package com.bulkapedia.compose.elements

import androidx.compose.runtime.MutableState
import com.bulkapedia.compose.data.repos.database.User

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

    class InfoAction(
        val show: MutableState<Boolean>,
        val text: MutableState<String>,
        val onClose: MutableState<() -> Unit>
    ): ScreenAction() {

        fun showInfo(text: String, onClose: () -> Unit = {}) {
            this.text.value = text
            this.onClose.value = onClose
            this.show.value = true
        }

    }

    class AddTagAction(
        val userState: MutableState<User?>,
        val show: MutableState<Boolean>,
        val defHero: MutableState<String>,
        val defKills: MutableState<String>,
        val defWR: MutableState<String>,
        val defRevives: MutableState<String>
    ): ScreenAction() {

        fun showAddTag(user: User, hero: String, kills: String, wr: String, revives: String) {
            userState.value = user
            defHero.value = hero
            defKills.value = kills
            defWR.value = wr
            defRevives.value = revives
            show.value = true
        }

    }

}