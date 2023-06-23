package com.bulkapedia.compose.screens.comments

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowDown
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.screens.sets.SetTabCard
import com.bulkapedia.R
import com.bulkapedia.compose.data.repos.comments.Comment
import com.bulkapedia.compose.elements.*
import com.bulkapedia.compose.ui.theme.*
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.clickable
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.DataStore
import com.bulkapedia.compose.data.nowTimeFormat
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.screens.Loading
import com.bulkapedia.compose.screens.titled.ScreenView

@Composable
fun CommentsScreen(setId: String, viewModel: CommentsViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val setState = viewModel.setFlow.collectAsState()
    val comments by viewModel.commentsFlow.collectAsState()
    // DataStore
    val dataStore = DataStore(LocalContext.current)
    val nickname by dataStore.getNickname.collectAsState("")
    // States
    val hideSetState = remember { mutableStateOf(false) }
    val txt = remember { mutableStateOf("") }
    val isEdit = remember { mutableStateOf(false) }
    val editComment = remember { mutableStateOf<Comment?>(null) }
    // UI
    ScreenView(title = "Комментарии", showBack = true) {
        when (val set = setState.value) {
            null -> Loading()
            else -> {
                ScreenWithDelete { delete ->
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Primary)
                            .animateContentSize()
                    ) {
                        if (!hideSetState.value) {
                            SetTabCard(set, set.from != nickname, disableComments = true, disableSettings = false) { s ->
                                delete.showDelete("Сет") { viewModel.deleteUserSet(s) }
                            }
                        }
                        IconToggleButton(checked = hideSetState.value, onCheckedChange = { hideSetState.value = it }) {
                            Icon(if (hideSetState.value) Icons.Filled.KeyboardDoubleArrowDown else Icons.Filled.KeyboardDoubleArrowUp, contentDescription = "show_user_set")
                        }
                        ChatRecycler(comments,
                            onSendLongClick = { comment, b ->
                                if (b) {
                                    delete.showDelete("комментарий") { viewModel.deleteComment(comment) }
                                } else {
                                    editComment.value = comment
                                    isEdit.value = true
                                    txt.value = comment.text
                                }
                            },
                            onRecLongClick = { comment -> navController.navigate("${Destinations.VISIT_PROFILE}/${comment.from}") {
                                launchSingleTop = true
                            }}
                        )
                        SendForm(set, viewModel, txt, isEdit, editComment)
                    }
                }
            }
        }
    }
    DisposableEffect(null) {
        viewModel.addListener(setId)
        onDispose { viewModel.dispose() }
    }
}

@Composable
fun ChatRecycler(
    comments: List<Comment>,
    onSendLongClick: (Comment, Boolean) -> Unit,
    onRecLongClick: (Comment) -> Unit
) {
    val nickname by DataStore(LocalContext.current).getNickname.collectAsState("")
    OutlinedCard {
        when (comments.isEmpty()) {
            true -> CenteredBox { Text(text = "Пока комментариев нет", color = Teal200) }
            else -> LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(PrimaryDark, RoundedCornerShape(20.dp))
                    .clipToBounds()
                    .padding(horizontal = 10.dp)
            ) {
                items(comments) { comment ->
                    val expanded = remember { mutableStateOf(false) }
                    if (comment.from == nickname) {
                        Box {
                            SendTextMessage(
                                text = comment.text,
                                date = comment.date) {
                                expanded.value = !expanded.value
                            }
                            SendCommentsDropdownMenu(expanded, {
                                onSendLongClick.invoke(comment, false)
                            }) {
                                onSendLongClick.invoke(comment, true)
                            }
                        }
                    } else {
                        Box {
                            ReceiverTextMessage(
                                text = comment.text,
                                date = comment.date,
                                author = comment.from) {
                                expanded.value = !expanded.value
                            }
                            ReceiveCommentsDropdownMenu(expanded) {
                                onRecLongClick.invoke(comment)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SendForm(
    set: UserSet,
    viewModel: CommentsViewModel,
    txt: MutableState<String>,
    isEdit: MutableState<Boolean>,
    editComment: MutableState<Comment?>
) {
    val nickname by DataStore(LocalContext.current).getNickname.collectAsState(initial = "")
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .background(Color.Transparent, RoundedCornerShape(50.dp)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        FormTextField(text = txt, placeholder = "Введите комментарий")
        Image(
            painter = painterResource(id = R.drawable.send),
            contentDescription = "send_image",
            colorFilter = ColorFilter.tint(Teal),
            modifier = Modifier
                .size(40.dp)
                .background(PrimaryDark, CircleShape)
                .border(2.dp, Teal200, CircleShape)
                .clip(CircleShape)
                .padding(10.dp)
                .clickable {
                    if (txt.value.isNotEmpty()) {
                        if (!isEdit.value) {
                            viewModel.sendComment(
                                Comment(
                                    set = set.id, from = nickname ?: "",
                                    text = txt.value, date = nowTimeFormat()
                                )
                            )
                        } else {
                            isEdit.value = false
                            viewModel.updateComment(editComment.value!!.copy(text = txt.value))
                        }
                        txt.value = ""
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
        modifier = Modifier
            .border(1.dp, Teal200, RoundedCornerShape(25.dp))
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
