package ru.bulkapedia.presentation.screens.registration

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bulkapedia.R
import ru.bulkapedia.domain.usecase.RegistrationUseCase
import ru.bulkapedia.presentation.mvi.BaseViewModel

class RegistrationViewModel(
    private val registrationUseCase: RegistrationUseCase
) : BaseViewModel<RegistrationViewState, RegistrationViewAction, RegistrationViewIntent>(
    initialState = RegistrationViewState()
) {

    override fun intent(intent: RegistrationViewIntent) {
        when (intent) {
            is RegistrationViewIntent.OnRegistrationClick -> onRegistrationClick()
            is RegistrationViewIntent.OnPasswordVisibilityChange -> onPasswordVisibilityChange()
            is RegistrationViewIntent.OnConfirmPasswordVisibilityChange -> onConfirmPasswordVisibilityChange()
            is RegistrationViewIntent.OnEmailChange -> onEmailChange(intent.value)
            is RegistrationViewIntent.OnPasswordChange -> onPasswordChange(intent.value)
            is RegistrationViewIntent.OnConfirmPasswordChange -> onConfirmPasswordChange(intent.value)
            is RegistrationViewIntent.OnNicknameChange -> onNicknameChange(intent.value)
            is RegistrationViewIntent.OnErrorChange -> onErrorChange(intent.value)
        }
    }

    private fun onPasswordVisibilityChange() {
        viewState = viewState.copy(passwordVisibility = !viewState.passwordVisibility)
    }

    private fun onConfirmPasswordVisibilityChange() {
        viewState = viewState.copy(confirmPasswordVisibility = !viewState.confirmPasswordVisibility)
    }

    private fun onEmailChange(value: String) {
        viewState = viewState.copy(email = value)
    }

    private fun onPasswordChange(value: String) {
        viewState = viewState.copy(password = value)
    }

    private fun onConfirmPasswordChange(value: String) {
        viewState = viewState.copy(confirmPassword = value)
    }

    private fun onNicknameChange(value: String) {
        viewState = viewState.copy(nickname = value)
    }

    private fun onErrorChange(value: String?) {
        viewState = viewState.copy(error = value)
        viewAction = null
    }

    private fun onRegistrationClick() {
        viewModelScope.launch {
            val result = registrationUseCase(
                email = viewState.email,
                password = viewState.password,
                nickname = viewState.nickname
            )
            viewAction = when (result) {
                R.string.ok -> RegistrationViewAction.IsRegistered
                else -> RegistrationViewAction.ShowError(result)
            }
        }
    }

}