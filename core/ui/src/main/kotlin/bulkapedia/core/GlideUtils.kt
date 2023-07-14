package bulkapedia.core

import androidx.compose.material.Text
import bulkapedia.core.theme.Teal200
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.integration.compose.placeholder

@OptIn(ExperimentalGlideComposeApi::class)
fun loadingPlaceholder(): Placeholder = placeholder { Loading() }

@OptIn(ExperimentalGlideComposeApi::class)
fun errorLoadImgPlaceholder(): Placeholder = placeholder {
    CenteredBox { Text("Ошибка загрузки изображения", color = Teal200) }
}