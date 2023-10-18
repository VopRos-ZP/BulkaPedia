package vopros.bulkapedia.ui.screens.comments

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import vopros.bulkapedia.R
import vopros.bulkapedia.comment.Comment
import vopros.bulkapedia.ui.components.HCenterBox
import vopros.bulkapedia.ui.components.ReceiverItem
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.SendItem
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.userSet.UserSetCard
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.utils.secondsToTime

@Destination
@Composable
fun CommentsScreen(viewModel: CommentsViewModel, setId: String) {
    val author by viewModel.author.collectAsState()
    val setState = viewModel.set.collectAsState()
    val commentsState = viewModel.comments.collectAsState()
    ScreenView(R.string.comments, showBack = true,
        viewModel = viewModel,
        fetch = { fetchComments(setId) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            when (val set = setState.value) {
                null -> HCenterBox(modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .background(BulkaPediaTheme.colors.primary, RoundedCornerShape(15.dp))
                    .height((25 * 2 + 75 * 3).dp)) { CircularProgressIndicator() }
                else -> UserSetCard(container = set, withHeroIcon = true)
            }
            when (val comments = commentsState.value) {
                null -> HCenterBox(modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .background(BulkaPediaTheme.colors.primary, RoundedCornerShape(15.dp))
                    .fillMaxHeight()) { CircularProgressIndicator() }
                emptyList<Comment>() -> HCenterBox(modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .background(BulkaPediaTheme.colors.primary, RoundedCornerShape(15.dp))
                    .fillMaxHeight()) { Text(resource = R.string.empty_sets) }
                else -> CommentsList(comments = comments, author = author)
            }
        }
    }
}

@Composable
fun CommentsList(comments: List<Comment>, author: String) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().animateContentSize(),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp)
    ) {
        items(comments) {
            when (it.author) {
                author -> SendItem(text = it.text, date = secondsToTime(it.date.seconds)) {

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

