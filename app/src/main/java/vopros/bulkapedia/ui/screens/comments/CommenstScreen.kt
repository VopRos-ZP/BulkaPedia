package vopros.bulkapedia.ui.screens.comments

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardDoubleArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import com.ramcosta.composedestinations.annotation.Destination
import vopros.bulkapedia.R
import vopros.bulkapedia.comment.Comment
import vopros.bulkapedia.ui.components.HCenterBox
import vopros.bulkapedia.ui.components.ReceiverItem
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.SendItem
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.TextRow
import vopros.bulkapedia.ui.components.userSet.UserSetCard
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.utils.secondsToTime

@Destination
@Composable
fun CommentsScreen(viewModel: CommentsViewModel, setId: String) {
    val author by viewModel.author.collectAsState()
    val setState = viewModel.set.collectAsState()
    val commentsState = viewModel.comments.collectAsState()
    val text = remember { mutableStateOf("") }
    val comment = remember(text) { mutableStateOf(Comment("", author, setId, text.value, Timestamp.now())) }
    val hideSetState = remember { mutableStateOf(false) }
    ScreenView(R.string.comments, showBack = true,
        viewModel = viewModel,
        fetch = { fetchComments(setId) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            when (val set = setState.value) {
                null -> HCenterBox(modifier = Modifier
                    .background(BulkaPediaTheme.colors.primary, RoundedCornerShape(15.dp))
                    .height((25 * 2 + 75 * 3).dp)) { CircularProgressIndicator(color = BulkaPediaTheme.colors.tintColor) }
                else -> AnimatedVisibility(!hideSetState.value) {
                    UserSetCard(container = set, withHeroIcon = true)
                }
            }
            IconToggleButton(
                checked = hideSetState.value,
                onCheckedChange = { hideSetState.value = it }
            ) {
                val rotate = animateFloatAsState(
                    targetValue = if (hideSetState.value) 180f else 0f,
                    animationSpec = tween(300), label = ""
                )
                Icon(
                    Icons.Filled.KeyboardDoubleArrowUp,
                    contentDescription = "show_user_set",
                    tint = BulkaPediaTheme.colors.tintColor,
                    modifier = Modifier.rotate(rotate.value)
                )
            }
            Box(modifier = Modifier.weight(1f).animateContentSize()) {
                when (val comments = commentsState.value) {
                    null -> HCenterBox(
                        modifier = Modifier
                            .background(BulkaPediaTheme.colors.primary, RoundedCornerShape(15.dp))
                            .fillMaxHeight()
                    ) { CircularProgressIndicator(color = BulkaPediaTheme.colors.tintColor) }
                    emptyList<Comment>() -> HCenterBox(
                        modifier = Modifier
                            .background(BulkaPediaTheme.colors.primary, RoundedCornerShape(15.dp))
                            .fillMaxHeight()
                    ) { Text(resource = R.string.empty_sets) }
                    else -> LazyColumn(
                        modifier = Modifier.animateContentSize().border(2.dp, BulkaPediaTheme.colors.primary, RoundedCornerShape(15.dp)).fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp)
                    ) {
                        items(comments) {
                            when (it.author) {
                                author -> SendItem(
                                    text = it.text,
                                    date = secondsToTime(it.date.seconds)
                                ) {

                                }

                                else -> ReceiverItem(
                                    text = it.text,
                                    date = secondsToTime(it.date.seconds),
                                    author = it.author
                                ) {

                                }
                            }
                        }
                    }
                }
            }
            TextRow(state = text, label = R.string.enter_text) {
                viewModel.sendComment(comment.value)
            }
        }
    }
}
