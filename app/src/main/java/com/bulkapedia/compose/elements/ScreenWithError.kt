@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun ScreenWithError(content: @Composable (ErrorAction) -> Unit) {
    val show = remember { mutableStateOf(false) }
    val text = remember { mutableStateOf("Неизвестная ошибка") }
    val onClose = remember { mutableStateOf({}) }

    val action = ErrorAction(show, text, onClose)
    CenteredBox (
        modifier = Modifier.fillMaxSize()
    ) {
        content.invoke(action)
        if (show.value) {
            ErrorDialog(action)
        }
    }
}

class ErrorAction (
    val show: MutableState<Boolean>,
    val text: MutableState<String>,
    val onClose: MutableState<() -> Unit>
) {

    fun showError(text: String, onClose: () -> Unit = {}) {
        this.text.value = text
        this.onClose.value = onClose
        this.show.value = true
    }

}
