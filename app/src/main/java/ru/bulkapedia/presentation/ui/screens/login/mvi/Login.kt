package ru.bulkapedia.presentation.ui.screens.login.mvi

object Login {

    data class State(
        val email: String = "",
        val password: String = "",
        val isShowPassword: Boolean = false,
        val error: String? = null
    )

    sealed interface Intent {
        data object CloseError: Intent
        data object LoginClick: Intent
        data object RegistrationClick: Intent
        data object ForgotPasswordClick: Intent
        data class EmailChanged(val value: String): Intent
        data class PasswordChanged(val value: String): Intent
        data class ToggleShowPassword(val value: Boolean): Intent
    }

    sealed interface Label {
        data object Success: Label
        data object RegistrationClick: Label
        data object ForgotPasswordClick: Label
    }

    sealed interface Msg {
        data class EmailChanged(val value: String): Msg
        data class PasswordChanged(val value: String): Msg
        data class ToggleShowPassword(val value: Boolean): Msg
        data class ErrorChanged(val value: String?): Msg
    }

    sealed interface Action

}