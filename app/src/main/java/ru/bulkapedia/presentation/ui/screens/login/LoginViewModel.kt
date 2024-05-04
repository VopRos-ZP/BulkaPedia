package ru.bulkapedia.presentation.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.repository.AuthRepository
import ru.bulkapedia.presentation.ui.screens.login.mvi.Login
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(Login.State())
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, throwable ->
        _state.value = state.value.copy(error = throwable.localizedMessage)
    }

    fun emailChanged(email: String) {
        _state.value = state.value.copy(email = email)
    }

    fun passwordChanged(password: String) {
        _state.value = state.value.copy(password = password)
    }

    fun toggleShowPassword() {
        _state.value = state.value.copy(isShowPassword = !state.value.isShowPassword)
    }

    fun closeError() {
        _state.value = state.value.copy(error = null)
    }

    fun loginClick(onSuccess: () -> Unit) {
        viewModelScope.launch(handler) {
            authRepository.signIn(state.value.email, state.value.password)
            onSuccess()
        }
    }

}