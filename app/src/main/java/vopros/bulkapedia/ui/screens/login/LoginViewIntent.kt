package vopros.bulkapedia.ui.screens.login

sealed class LoginViewIntent {
    object Start: LoginViewIntent()
    data class Login(val email: String, val password: String): LoginViewIntent()
}