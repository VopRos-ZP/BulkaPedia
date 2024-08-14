package ru.bulkapedia.presentation.screens.login

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bulkapedia.R
import ru.bulkapedia.domain.usecase.LoginUseCase
import ru.bulkapedia.presentation.mvi.BaseViewModel

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginViewState, LoginViewAction, LoginViewIntent>(
    initialState = LoginViewState()
) {

    override fun intent(intent: LoginViewIntent) {
        when (intent) {
            is LoginViewIntent.OnLoginClick -> onLoginClick()
            is LoginViewIntent.OnVisibilityChange -> onShowPasswordToggle()
            is LoginViewIntent.OnEmailChange -> onEmailChange(intent.value)
            is LoginViewIntent.OnPasswordChange -> onPasswordChange(intent.value)
            is LoginViewIntent.OnErrorChange -> onErrorChange(intent.value)
        }
    }

    private fun onEmailChange(value: String) {
        viewState = viewState.copy(email = value)
    }

    private fun onPasswordChange(value: String) {
        viewState = viewState.copy(password = value)
    }

    private fun onShowPasswordToggle() {
        viewState = viewState.copy(isShowPassword = !viewState.isShowPassword)
    }

    private fun onErrorChange(value: String?) {
        viewState = viewState.copy(error = value)
        viewAction = null
    }

    private fun onLoginClick() {
        viewModelScope.launch {
            val isLogged = loginUseCase(viewState.email, viewState.password)
            viewAction = when (isLogged) {
                true -> LoginViewAction.IsLogged
                else -> LoginViewAction.ShowError(R.string.error_login_password)
            }
        }
    }

}