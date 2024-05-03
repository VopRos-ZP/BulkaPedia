package ru.bulkapedia.presentation.ui.screens.registration.mvi

object Registration {

    data class State(
        val email: String = "",
        val password: String = "",
        val isShowPassword: Boolean = false,
        val confirmPassword: String = "",
        val isShowConfirmPassword: Boolean = false,
        val nickname: String = "",
        val error: String? = null
    )

    sealed interface Intent {
        data class EmailChanged(val value: String) : Intent
        data class PasswordChanged(val value: String) : Intent
        data class ConfirmPasswordChanged(val value: String) : Intent
        data class NicknameChanged(val value: String) : Intent
        data object ErrorChanged : Intent
        data object RegistrationClick : Intent
        data object NavigationBackClick : Intent
        data object ToggleShowPassword : Intent
        data object ToggleShowConfirmPassword : Intent
    }

    sealed interface Label {
        data object BackToLogin : Label
        data object Success : Label
    }

    sealed interface Msg {
        data class EmailChanged(val value: String) : Msg
        data class PasswordChanged(val value: String) : Msg
        data class ConfirmPasswordChanged(val value: String) : Msg
        data class NicknameChanged(val value: String) : Msg
        data class ErrorChanged(val value: String?) : Msg
        data class ShowPasswordChanged(val value: Boolean) : Msg
        data class ShowConfirmPasswordChanged(val value: Boolean) : Msg
    }

    sealed interface Action

}