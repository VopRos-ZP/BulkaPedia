package com.bulkapedia.compose.screens.login

import androidx.navigation.NavController
import com.bulkapedia.compose.data.User

sealed class LoginEvent {
    object EnterScreen: LoginEvent()
    data class ErrorScreen(val error: String): LoginEvent()
    data class OnSignInClick(
        val email: String,
        val password: String,
        val onSuccess: (User) -> Unit
    ): LoginEvent()
    data class OnForgotPasswordClick(val navigate: (NavController) -> Unit): LoginEvent()
}