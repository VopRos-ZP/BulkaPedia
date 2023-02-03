@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun ScreenWithError(
    show: MutableState<Boolean>,
    text: String,
    onClose: () -> Unit = {},
    content: @Composable () -> Unit
) {
    CenteredBox {
        content.invoke()
        if (show.value) {
            ErrorDialog(msg = text, state = show, onDismiss = onClose)
        }
    }
}