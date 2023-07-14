package bulkapedia.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import bulkapedia.core.theme.Teal200

@Composable
fun Loading() {
    Box(modifier = Modifier.fillMaxSize()) { CircularProgressIndicator(color = Teal200) }
}