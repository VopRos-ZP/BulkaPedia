package vopros.bulkapedia.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage

@Composable
fun Image(url: String, modifier: Modifier = Modifier) {
//    val context = LocalContext.current
//    LaunchedEffect(Unit) {
//        val request = ImageRequest.Builder(context)
//            .data(url)
//            .build()
//        context.imageLoader.enqueue(request)
//    }
    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier
    )
}