@file:Suppress("FunctionName")
package com.bulkapedia.compose.screens.comments

import android.text.format.DateFormat
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.navigation.ToolbarCtx
import com.bulkapedia.compose.screens.sets.SetTabCard
import com.bulkapedia.R
import com.bulkapedia.compose.data.Comment
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.elements.*
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.DataStore
import java.util.*

@Composable
fun CommentsScreen(
    ctx: ToolbarCtx,
    setId: String,
    viewModel: CommentsViewModel,
    commentsVM: CommentsVM
) {
    ctx.observeAsState()
    ctx.setData(title = "Коментарии", showBackButton = true)
    // vars
    val nickname by DataStore(LocalContext.current).getNickname.collectAsState(initial = "")
    val commentsList = remember { mutableStateListOf<Comment>() }
    val setState = viewModel.setLiveData.observeAsState()
    val cmsState = commentsVM.liveData.observeAsState()
    //UI
    ScreenWithDelete { action ->
        when (val set = setState.value) {
            null -> CommentsLoading()
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Primary)
                ) {
                    SetTabCard(set, set.from != nickname, disableComments = true, disableSettings = false) { s ->
                        action.showDelete("Сет") { Database().removeSet(s) }
                    }
                    ChatRecycler(cmsState.value ?: emptyList())
                    SendForm(set, commentsVM)
                }
            }
        }
    }
    // Listeners
    DisposableEffect(null) {
        viewModel.addListener(setId)
        commentsVM.addListener(setId, commentsList)
        onDispose {
            viewModel.removeListener()
            commentsVM.removeListener()
        }
    }
}

@Composable
fun CommentsLoading() {
    CenteredBox (modifier = Modifier.fillMaxSize().background(Primary)) {
        CircularProgressIndicator(color = Teal200)
    }
}

@Composable
fun ChatRecycler(comments: List<Comment>) {
    val nickname by DataStore(LocalContext.current).getNickname.collectAsState(initial = "")
    Card(
        backgroundColor = PrimaryDark,
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(2.dp, Teal200),
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.68f)
            .padding(horizontal = 20.dp)
            .background(PrimaryDark, RoundedCornerShape(20.dp))
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryDark, RoundedCornerShape(20.dp))
                .clipToBounds()
                .padding(horizontal = 10.dp)
        ) {
            if (comments.isEmpty()) {
                item { Text(text = "Пока комментариев нет", color = Teal) }
            } else {
                items(comments) { comment ->
                    if (comment.from == nickname) {
                        SendTextMessage(
                            text = comment.text,
                            date = comment.date
                        )
                    } else {
                        ReceiverTextMessage(
                            text = comment.text,
                            date = comment.date,
                            author = comment.from
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SendForm(set: UserSet, viewModel: CommentsVM) {
    val text = remember { mutableStateOf("") }
    val nickname by DataStore(LocalContext.current).getNickname.collectAsState(initial = "")
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.Transparent, RoundedCornerShape(50.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FormTextField(
            text = text,
            placeholder = "Введите комментарий"
        )
        Image(
            painter = painterResource(id = R.drawable.send),
            contentDescription = "send_image",
            colorFilter = ColorFilter.tint(Teal),
            modifier = Modifier
                .size(45.dp)
                .background(PrimaryDark, CircleShape)
                .border(2.dp, Teal200, CircleShape)
                .clip(CircleShape)
                .padding(10.dp)
                .clickable {
                    if (text.value.isNotEmpty()) {
                        val calendar = Calendar.getInstance(Locale.getDefault())
                        val date = DateFormat.format("dd.MM.yyyy HH:mm:ss", calendar).toString()
                        viewModel.sendComment(Comment(
                            set.userSetId, nickname ?: "",
                            text.value, date
                        ))
                        text.value = ""
                    }
                }
        )
    }
}

@Composable
fun FormTextField(
    text: MutableState<String>,
    placeholder: String,
) {
    OutlinedTextField(
        modifier = Modifier.border(1.dp, Teal200, RoundedCornerShape(25.dp))
            .clip(RoundedCornerShape(25.dp)),
        value = text.value,
        onValueChange = { text.value = it },
        shape = RoundedCornerShape(25.dp),
        placeholder = { Text(text = placeholder, color = Teal) },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = PrimaryDark,
            textColor = Color.White,
            cursorColor = Teal,
            focusedBorderColor = Teal200,
            unfocusedBorderColor = Teal200,
            focusedLabelColor = Teal200,
            unfocusedLabelColor = Teal,
            placeholderColor = Teal
        ),
    )
}
