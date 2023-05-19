package com.bulkapedia.compose.screens.login

import com.vopros.domain.user.User

sealed class LoginViewState {
    object Loading: LoginViewState()
    data class Error(val error: String): LoginViewState()
    data class Success(val user: User): LoginViewState()
}