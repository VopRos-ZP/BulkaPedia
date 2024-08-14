package ru.bulkapedia.presentation.screens.login

data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val isShowPassword: Boolean = false,
    val error: String? = null
)
