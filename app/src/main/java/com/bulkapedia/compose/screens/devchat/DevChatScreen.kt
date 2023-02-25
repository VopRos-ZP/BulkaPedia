@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.devchat

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.text.format.DateFormat
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import com.bulkapedia.compose.data.Message
import com.bulkapedia.compose.elements.ReceiverTextMessage
import com.bulkapedia.compose.elements.SendTextMessage
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.screens.comments.FormTextField
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.clickable
import com.google.firebase.storage.FirebaseStorage
import java.util.*

@Composable
fun DevChat(ctx: ToolbarCtx, author: String, receiver: String, viewModel: DevChatViewModel) {
    val context = LocalContext.current
    // Toolbar
    ctx.observeAsState()
    ctx.setData(if (author == ADMIN_NICKNAME) receiver else "Разработчик", showBackButton = true, false)
    // Vars
    val text = remember { mutableStateOf("") }
    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val bitmap =  remember { mutableStateOf<Bitmap?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        imageUri.value = uri
    }
    val userState = viewModel.user.observeAsState()
    val viewState = viewModel.messages.observeAsState()
    // UI
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.923f)
    ) {
        // recycler
        MessagesRecycler(author, viewState.value ?: emptyList())
        // send form
        Row (
            modifier = Modifier.fillMaxSize()
                .padding(start = 20.dp, bottom = 20.dp, end = 20.dp)
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
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, imageUri.value!!)
                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, imageUri.value!!)
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
                            if (text.value.isEmpty() && imageUri.value == null && userState.value == null) return@clickable
                            val calendar = Calendar.getInstance(Locale.getDefault())
                            val date = DateFormat.format("dd.MM.yyyy HH:mm:ss", calendar).toString()
                            if (imageUri.value != null) {
                                val fileName = getFileName(context.contentResolver, imageUri.value!!)
                                val path = StringBuilder(userState.value!!.email).append("/").append(fileName).toString()
                                val ref = FirebaseStorage.getInstance().reference.child(path)
                                ref.putFile(imageUri.value!!).continueWithTask {
                                    return@continueWithTask ref.downloadUrl
                                }.addOnSuccessListener { url ->
                                    viewModel.obtainEvent(
                                        DevChatEvent.SendMessage(
                                            Message(author, date, url.toString(), receiver, "", false)
                                        )
                                    )
                                }
                            } else {
                                viewModel.obtainEvent(
                                    DevChatEvent.SendMessage(
                                        Message(author, date, "", receiver, text.value, false)
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
    // Listeners
    DisposableEffect(null) {
        viewModel.userListener(author)
        viewModel.obtainEvent(DevChatEvent.LoadMessages(author, receiver))
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun MessagesRecycler(nickname: String, messages: List<Message>) {
    Card(
        backgroundColor = Teal,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.885f)
            .padding(20.dp)
            .background(PrimaryDark, RoundedCornerShape(20.dp))
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryDark, RoundedCornerShape(20.dp))
                .clipToBounds()
                .padding(horizontal = 10.dp)
        ) {
            if (messages.isEmpty()) {
                item { Text(text = "Сообщений нет", color = Teal) }
            } else {
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

