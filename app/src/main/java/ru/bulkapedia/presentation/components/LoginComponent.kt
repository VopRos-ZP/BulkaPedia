package ru.bulkapedia.presentation.components

import kotlinx.coroutines.flow.StateFlow

interface LoginComponent {

    data class Model(
        val email: String,
        val password: String
    )

    val model: StateFlow<Model>

    fun onEmailChanged(email: String)

    fun onPasswordChanged(password: String)

    fun onLoginClick()

    fun onSignUpClick()

    fun onForgetPasswordClick()

}