package ru.bulkapedia.presentation.screens.login

sealed interface LoginViewAction {
    data class ShowDialog(val text: String) : LoginViewAction
}