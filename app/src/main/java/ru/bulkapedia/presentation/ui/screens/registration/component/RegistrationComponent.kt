package ru.bulkapedia.presentation.ui.screens.registration.component

import kotlinx.coroutines.flow.StateFlow
import ru.bulkapedia.presentation.ui.screens.registration.mvi.Registration

interface RegistrationComponent {

    val state: StateFlow<Registration.State>

    fun onEmailChanged(email: String)
    fun onPasswordChanged(password: String)
    fun onConfirmPasswordChanged(confirmPassword: String)
    fun onNicknameChanged(nickname: String)
    fun onNavBackClick()
    fun onRegistrationClick()
}