package com.bulkapedia.compose.elements

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.bulkapedia.compose.LockLandscape
import com.bulkapedia.compose.LockPortrait
import com.bulkapedia.compose.data.snackbars.TextSnackbar
import com.bulkapedia.compose.ui.theme.Primary
import com.bulkapedia.compose.util.BackHandler
import com.bulkapedia.compose.util.CenteredBox
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
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
    val modifier = if (isFullScreen.value) {
        LockLandscape()
        Modifier.fillMaxWidth(0.8f)
    } else {
        LockPortrait()
        Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(bottom = 20.dp)
            .background(Color.Transparent)
    }
    when (isFullScreen.value) {
        true -> CenteredBox {
            WebView(modifier, video, isFullScreen) {  }
        }
        false -> TextSnackbar { action ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Primary)
                    .padding(horizontal = 20.dp)
            ) {
                WebView(modifier, video, isFullScreen) {
                    scope.launch { action.showSnackbar(it) }
                }
                content.invoke(this)
            }
        }
    }
}

@Composable
fun WebView(
    modifier: Modifier,
    video: String,
    isFullScreen: MutableState<Boolean>,
    onError: (String) -> Unit
) {
    BackHandler {
        if (isFullScreen.value) {
            isFullScreen.value = false
        }
    }
    AndroidView(factory = {

        val youtubeView = YouTubePlayerView(it).apply {
            enableAutomaticInitialization = false
            enableBackgroundPlayback(false)
        }
        youtubeView.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(
                fullscreenView: View,
                exitFullscreen: () -> Unit
            ) {
                isFullScreen.value = true
            }

            override fun onExitFullscreen() {
                isFullScreen.value = false
            }

        })
        val listener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.cueVideo(video, 0f)
            }
            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
                super.onError(youTubePlayer, error)
                onError(error.toString())
            }
        }
        youtubeView.initialize(listener, IFramePlayerOptions
            .Builder()
            .controls(1)
            .fullscreen(1)
            .build()
        )
        youtubeView
    }, modifier = modifier)
}
