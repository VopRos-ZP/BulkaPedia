package ru.bulkapedia.presentation.ui.screens.registration

data class RegistrationViewState(
    val email: String = "",
    val password: String = "",
    val nickname: String = "",
    val isShowPassword: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isShowError: Boolean = false,
)
