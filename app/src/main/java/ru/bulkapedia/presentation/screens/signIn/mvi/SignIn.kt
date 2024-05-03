package ru.bulkapedia.presentation.screens.signIn.mvi

class SignIn {

    sealed interface Intent {
        data class EmailChanged(val value: String) : Intent
        data class PasswordChanged(val value: String) : Intent
        data object CloseError : Intent
        data object LoginClick : Intent
    }

    data class State(
        val isLoading: Boolean = false,
        val email: String = "",
        val password: String = "",
        val message: String? = null
    )

    sealed interface Label {
        data class Navigate(val direction: String): Label
        data class Snackbar(val message: String): Label
    }

    sealed interface Msg {
        data class Loading(val isLoading: Boolean) : Msg
        data class EmailChanged(val value: String) : Msg
        data class PasswordChanged(val value: String) : Msg
        data class Error(val message: String? = null) : Msg
    }

}