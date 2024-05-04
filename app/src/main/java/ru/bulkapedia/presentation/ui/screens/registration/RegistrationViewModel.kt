package ru.bulkapedia.presentation.ui.screens.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.User
import ru.bulkapedia.domain.repository.AuthRepository
import ru.bulkapedia.domain.repository.UserRepository
import ru.bulkapedia.presentation.ui.screens.registration.mvi.Registration
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(Registration.State())
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

    fun confirmPasswordChanged(password: String) {
        _state.value = state.value.copy(confirmPassword = password)
    }

    fun nicknameChanged(nickname: String) {
        _state.value = state.value.copy(nickname = nickname)
    }

    fun toggleShowPassword() {
        _state.value = state.value.copy(isShowPassword = !state.value.isShowPassword)
    }

    fun toggleShowConfirmPassword() {
        _state.value = state.value.copy(isShowConfirmPassword = !state.value.isShowConfirmPassword)
    }

    fun closeError() {
        _state.value = state.value.copy(error = null)
    }

    fun registrationClick(onSuccess: () -> Unit) {
        viewModelScope.launch(handler) {
            authRepository.signUp(state.value.email, state.value.password)?.let {
                userRepository.upsert(User(id = it, nickname = state.value.nickname))
                onSuccess()
            }
        }
    }

}