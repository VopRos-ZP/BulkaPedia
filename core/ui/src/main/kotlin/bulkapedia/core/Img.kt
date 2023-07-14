package bulkapedia.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Img(
    url: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit
) {
    GlideImage(
        model = url,
        contentDescription = null,
        loading = loadingPlaceholder(),
        failure = errorLoadImgPlaceholder(),
        modifier = modifier,
        contentScale = contentScale
    )
}