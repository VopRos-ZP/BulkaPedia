package ru.bulkapedia.presentation.screens.registration

sealed interface RegistrationViewIntent {
    data object OnRegistrationClick : RegistrationViewIntent
    data object OnPasswordVisibilityChange : RegistrationViewIntent
    data object OnConfirmPasswordVisibilityChange : RegistrationViewIntent
    data class OnEmailChange(val value: String) : RegistrationViewIntent
    data class OnPasswordChange(val value: String) : RegistrationViewIntent
    data class OnConfirmPasswordChange(val value: String) : RegistrationViewIntent
    data class OnNicknameChange(val value: String) : RegistrationViewIntent
    data class OnErrorChange(val value: String? = null) : RegistrationViewIntent
}