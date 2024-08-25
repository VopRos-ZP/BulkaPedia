package ru.bulkapedia.presentation.screens.login

object Login {

    data class State(
        val email: String = "",
        val password: String = "",
        val showPassword: Boolean = false,
        val error: String? = null,
    )

    sealed interface Intent {
        data class OnEmailChange(val value: String) : Intent
        data class OnPasswordChange(val value: String) : Intent
        data class OnErrorChange(val value: String?) : Intent
        data object OnShowPasswordChange : Intent
        data object OnLoginClick : Intent
        data object OnRegistrationClick : Intent
        data object OnForgotPasswordClick : Intent
    }

    sealed interface Label {
        data object NavToProfile : Label
        data object NavToRegistration : Label
        data object NavToForgotPassword : Label
    }

    sealed interface Action

    sealed interface Msg {
        data class OnEmailChange(val value: String) : Msg
        data class OnPasswordChange(val value: String) : Msg
        data class OnErrorChange(val value: String?) : Msg
        data object OnShowPasswordChange : Msg
    }

}
