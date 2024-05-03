package ru.bulkapedia.presentation.screens.wiki.mvi

object Wiki {

    sealed interface Intent {
        data object Launch
        data class EmailChanged(val value: String) : Intent
        data class PasswordChanged(val value: String) : Intent
        data object CloseError : Intent
        data object LoginClick : Intent
    }

}