package com.bulkapedia.compose.screens.devchat

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore.Images.Media.getBitmap
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ADMIN_NICKNAME
import com.bulkapedia.R
import com.bulkapedia.compose.data.nowTimeFormat
import com.bulkapedia.data.messages.Message
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.elements.ReceiverTextMessage
import com.bulkapedia.compose.elements.SendTextMessage
import com.bulkapedia.compose.screens.comments.FormTextField
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.clickable
import com.google.firebase.storage.FirebaseStorage
import java.util.*

@Composable
fun DevChat(author: String, receiver: String, viewModel: DevChatViewModel) {
    val context = LocalContext.current

    val text = remember { mutableStateOf("") }
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val bitmap =  remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri.value = uri
    }
    val messages = viewModel.messages

    ScreenView(title = if (author == ADMIN_NICKNAME) receiver else "Разработчик", showBack = true) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Primary)
                .padding(top = 20.dp)
                .animateContentSize()
        ) {
            Box(modifier = Modifier.weight(1f)) {
                MessagesRecycler(author, messages)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(Color.Transparent, RoundedCornerShape(50.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (imageUri.value == null) {
                    FormTextField(
                        text = text,
                        placeholder = "Введите текст"
                    )
                } else {
                    if (Build.VERSION.SDK_INT < 28) {
                        bitmap.value = getBitmap(context.contentResolver, imageUri.value!!)
                    } else {
                        val source = ImageDecoder.createSource(context.contentResolver, imageUri.value!!)
                        bitmap.value = ImageDecoder.decodeBitmap(source)
                    }
                    Image(
                        bitmap.value!!.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier.height(60.dp)
                    )
                }
                if (text.value.isEmpty()) {
                    Image(
                        painter = painterResource(id = R.drawable.attach),
                        contentDescription = "send_image",
                        colorFilter = ColorFilter.tint(Teal),
                        modifier = Modifier
                            .size(57.dp)
                            .background(PrimaryDark, CircleShape)
                            .border(2.dp, Teal200, CircleShape)
                            .clip(CircleShape)
                            .padding(12.dp)
                            .clickable { launcher.launch("image/*") }
                    )
                }
                if (text.value.isNotEmpty() || imageUri.value != null) {
                    Image(
                        painter = painterResource(id = R.drawable.send),
                        contentDescription = "send_text",
                        colorFilter = ColorFilter.tint(Teal),
                        modifier = Modifier
                            .size(57.dp)
                            .background(PrimaryDark, CircleShape)
                            .border(2.dp, Teal200, CircleShape)
                            .clip(CircleShape)
                            .padding(12.dp)
                            .clickable {
                                if (text.value.isEmpty() && imageUri.value == null) return@clickable
                                if (imageUri.value != null) {
                                    val fileName =
                                        getFileName(context.contentResolver, imageUri.value!!)
                                    val path = StringBuilder(author)
                                        .append("/")
                                        .append(fileName)
                                        .toString()
                                    val ref = FirebaseStorage.getInstance().reference.child(path)
                                    ref
                                        .putFile(imageUri.value!!)
                                        .continueWithTask {
                                            return@continueWithTask ref.downloadUrl
                                        }
                                        .addOnSuccessListener { url ->
                                            viewModel.sendMessage(
                                                Message(
                                                    "",
                                                    author, nowTimeFormat(), url.toString(),
                                                    receiver, "", false
                                                )
                                            )
                                        }
                                } else {
                                    viewModel.sendMessage(
                                        Message(
                                            "",
                                            author, nowTimeFormat(), "",
                                            receiver, text.value, false
                                        )
                                    )
                                }
                                text.value = ""
                                imageUri.value = null
                            }
                    )
                }
            }
        }
    }
    // Listeners
    DisposableEffect(null) {
        viewModel.fetchMessages(author, receiver)
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun MessagesRecycler(nickname: String, messages: SnapshotStateList<Message>) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        when (messages.isEmpty()) {
            true -> CenteredBox { Text(text = "Сообщений нет", color = Teal200) }
            else -> LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PrimaryDark, RoundedCornerShape(20.dp))
                    .clipToBounds()
                    .padding(horizontal = 10.dp)
            ) {
                items(messages) { message ->
                    if (message.author == nickname) {
                        SendTextMessage(
                            text = message.text,
                            date = message.date,
                            image = message.image
                        ) {}
                    } else {
                        ReceiverTextMessage(
                            text = message.text,
                            date = message.date,
                            author = message.author,
                            image = message.image
                        ) {}
                    }
                }
            }
        }
    }
}

@SuppressLint("Range")
private fun getFileName(contentResolver: ContentResolver, image: Uri): String {
    var result: String? = null
    if (image.scheme.equals("content")) {
        try {
            val cursor = contentResolver.query(image, null, null, null)
            cursor.use {
                if (it != null && it.moveToFirst()) {
                    result = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
                it?.close()
            }
        } catch (_: Exception) {}
    }
    if (result == null) {
        result = image.path
        val cut = result!!.lastIndexOf("/")
        if (cut != -1)
            result = result!!.substring(cut + 1)
    }
    return result!!
}

