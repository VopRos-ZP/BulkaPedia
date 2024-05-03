package ru.bulkapedia.presentation.components

import kotlinx.coroutines.flow.StateFlow
import ru.bulkapedia.presentation.ui.screens.login.mvi.Login

interface LoginComponent {

    val model: StateFlow<Login.State>

    fun onEmailChanged(email: String)

    fun onPasswordChanged(password: String)

    fun onLoginClick()

    fun onSignUpClick()

    fun onForgetPasswordClick()

}