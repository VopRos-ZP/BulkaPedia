package com.bulkapedia.compose.screens.passwordreset

import com.bulkapedia.compose.data.User

sealed class PasswordResetEvent {
    object NoEvent: PasswordResetEvent()
    data class OnResetClick(val email: String, val newPassword: String): PasswordResetEvent()
    data class CheckEmail(val email: String): PasswordResetEvent()
    data class CheckNewPassword(
        val email: String,
        val password: String,
        val saveStore: (User) -> Unit
    ): PasswordResetEvent()
}