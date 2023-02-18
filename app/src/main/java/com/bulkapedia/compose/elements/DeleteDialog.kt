@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun ScreenWithDelete(content: @Composable (ScreenAction.DeleteAction) -> Unit) {
    val show = remember { mutableStateOf(false) }
    val whatDelete = remember { mutableStateOf("") }
    val onDelete = remember { mutableStateOf({}) }
    val action = ScreenAction.DeleteAction(whatDelete, show, onDelete)
    CenteredBox {
        content.invoke(action)
        AnimatedVisibility(show.value) {
            DeleteDialog(action)
        }
    }
}

@Composable
fun DeleteDialog(action: ScreenAction.DeleteAction) {
    ConfirmDialog(
        state = action.show,
        title = "Удалить ${action.whatDelete.value}",
        text = "Вы действительно хотите удалить ${action.whatDelete.value}?",
        onConfirm = action.onDelete.value
    )
}