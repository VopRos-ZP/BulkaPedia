package com.bulkapedia.compose.elements

import androidx.compose.runtime.MutableState

sealed class ScreenAction {

    data class DeleteAction(
        var whatDelete: MutableState<String>,
        var show: MutableState<Boolean>,
        var onDelete: MutableState<() -> Unit>,
    ): ScreenAction() {

        fun showDelete(whatDelete: String,  onDelete: () -> Unit) {
            this.whatDelete.value = whatDelete
            this.onDelete.value = onDelete
            show.value = true
        }

    }

    class ErrorAction (
        val show: MutableState<Boolean>,
        val text: MutableState<String>,
        val onClose: MutableState<() -> Unit>
    ): ScreenAction() {

        fun showError(text: String, onClose: () -> Unit = {}) {
            this.text.value = text
            this.onClose.value = onClose
            this.show.value = true
        }

    }

}