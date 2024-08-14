package ru.bulkapedia.presentation.screens.registration

data class RegistrationViewState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val confirmPassword: String = "",
    val confirmPasswordVisibility: Boolean = false,
    val nickname: String = "",
    val error: String? = null
)
