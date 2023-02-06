@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun ScreenWithError(
    show: MutableState<Boolean>,
    text: String,
    onClose: () -> Unit = {},
    content: @Composable () -> Unit
) {
    CenteredBox (
        modifier = Modifier.fillMaxWidth()
            .fillMaxHeight(fraction = 0.923f)
    ) {
        content.invoke()
        if (show.value) {
            ErrorDialog(msg = text, state = show, onDismiss = onClose)
        }
    }
}