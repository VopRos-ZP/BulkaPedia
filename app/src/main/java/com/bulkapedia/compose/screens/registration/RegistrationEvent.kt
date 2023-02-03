package com.bulkapedia.compose.screens.registration

sealed class RegistrationEvent {
    object EnterScreen: RegistrationEvent()
    data class ErrorScreen(val error: String): RegistrationEvent()
    data class OnSignUpClick(
        val email: String,
        val password: String,
        val nickname: String,
        val onSuccess: () -> Unit
    ): RegistrationEvent()
}