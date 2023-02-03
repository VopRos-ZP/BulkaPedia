@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.bulkapedia.compose.util.CenteredBox

@Composable
fun ScreenWithDelete(
    whatDelete: String,
    whenShow: (() -> Boolean)? = null,
    show: MutableState<Boolean>,
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    CenteredBox {
        content.invoke()
        if (whenShow == null) {
            if (show.value) DeleteDialog(whatDelete, show, onDelete)
        } else {
            if (show.value && whenShow.invoke()) {
                DeleteDialog(whatDelete, show, onDelete)
            }
        }
    }
}

@Composable
fun DeleteDialog(
    whatDelete: String,
    show: MutableState<Boolean>,
    onDelete: () -> Unit
) {
    ConfirmDialog(
        state = show,
        title = "Удалить $whatDelete",
        text = "Вы действительно хотите удалить $whatDelete?",
        onConfirm = onDelete
    )
}