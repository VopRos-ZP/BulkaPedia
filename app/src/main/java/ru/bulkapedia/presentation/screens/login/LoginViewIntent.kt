package ru.bulkapedia.presentation.screens.login

sealed interface LoginViewIntent {
    data object OnLoginClick : LoginViewIntent
    data object OnShowPasswordToggle : LoginViewIntent
    data class OnEmailChange(val value: String) : LoginViewIntent
    data class OnPasswordChange(val value: String) : LoginViewIntent
}