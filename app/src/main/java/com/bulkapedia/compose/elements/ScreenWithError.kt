@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun ScreenWithError(content: @Composable (ScreenAction.ErrorAction) -> Unit) {
    val show = remember { mutableStateOf(false) }
    val text = remember { mutableStateOf("Неизвестная ошибка") }
    val onClose = remember { mutableStateOf({}) }

    val action = ScreenAction.ErrorAction(show, text, onClose)
    CenteredBox (
        modifier = Modifier.fillMaxSize()
    ) {
        content.invoke(action)
        if (show.value) {
            ErrorDialog(action)
        }
    }
}
