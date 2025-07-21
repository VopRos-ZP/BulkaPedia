package ru.bulkapedia.presentation.ui.screens.registration

sealed interface RegistrationSideEffect {
    data object OnLoginClick : RegistrationSideEffect
    data object OnSuccessRegistration : RegistrationSideEffect
}