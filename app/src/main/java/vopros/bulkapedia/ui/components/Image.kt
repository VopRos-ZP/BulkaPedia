package vopros.bulkapedia.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Image(url: String, modifier: Modifier = Modifier) {
    GlideImage(
        model = url,
        contentDescription = null,
        modifier = modifier
    )
}