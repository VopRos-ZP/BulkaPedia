@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScreenWithError(content: @Composable (ScreenAction.ErrorAction) -> Unit) {
    val show = remember { mutableStateOf(false) }
    val text = remember { mutableStateOf("Неизвестная ошибка") }
    val onClose = remember { mutableStateOf({}) }

    val action = ScreenAction.ErrorAction(show, text, onClose)
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        content.invoke(action)
        if (show.value) {
            ErrorDialog(action)
        }
    }
}
