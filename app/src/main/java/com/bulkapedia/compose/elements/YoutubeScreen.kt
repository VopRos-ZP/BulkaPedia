@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bulkapedia.compose.data.snackbars.TextSnackbar
import com.bulkapedia.compose.ui.theme.Primary
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import kotlinx.coroutines.launch

/**
 * @param video is video id for example: comM42_dsa
 * @param content is composable function, which alignment under YouTube View
 * **/
@Composable
fun YouTubeScreen(
    video: String,
    isFullScreen: MutableState<Boolean>,
    content: @Composable ColumnScope.() -> Unit
) {
    val scope = rememberCoroutineScope()
    /** Enable for full screen **/
//    val modifier = if (isFullScreen.value) {
//        Modifier.fillMaxSize()
//    } else {
//        Modifier.fillMaxWidth().height(250.dp)
//    }
    TextSnackbar { action ->
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Primary)
                .padding(horizontal = 20.dp)
        ) {
            AndroidView(factory = {
                val youtubeView = YouTubePlayerView(it)
                youtubeView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.cueVideo(video, 0f)
                    }
                    override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                        super.onError(youTubePlayer, error)
                        scope.launch { action.showSnackbar(error.toString()) }
                    }
                })
                youtubeView
            }, modifier = Modifier.fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 20.dp)
                .background(Color.Transparent)
            )
            content.invoke(this)
        }
    }
}
