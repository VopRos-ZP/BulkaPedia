package ru.bulkapedia.presentation.screens.registration

sealed interface RegistrationViewAction {
    data object IsRegistered : RegistrationViewAction
    data class ShowError(val text: Int) : RegistrationViewAction
}