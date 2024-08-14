package ru.bulkapedia.presentation.screens.login

sealed interface LoginViewAction {
    data object IsLogged : LoginViewAction
    data class ShowError(val text: Int) : LoginViewAction
}