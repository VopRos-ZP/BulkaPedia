package com.bulkapedia.compose.screens.passwordreset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bulkapedia.users.User
import bulkapedia.users.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordResetViewModel @Inject constructor(
    private val usersRepository: UserRepository
) : ViewModel() {

    private val _emailFlow: MutableStateFlow<String> = MutableStateFlow("")
    val emailFlow: StateFlow<String> = _emailFlow

    private val _emailCheckFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val emailCheckFlow: StateFlow<Boolean> = _emailCheckFlow

    private val _errorFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorFlow: StateFlow<String?> = _errorFlow

    fun checkPassword(email: String, password: String, save: suspend (User) -> Unit) {
        viewModelScope.launch {
            usersRepository.fetchAll().find { it.email == email }?.let {
                if (it.password == password) {
                    viewModelScope.launch {
                        _errorFlow.emit(null)
                        updateUser(it)
                        save(it)
                    }
                } else {
                    _errorFlow.emit("Пароли не совпадают, пожалуйста введите новый пароль")
                }
            }
        }
    }

    fun checkEmail(email: String) {
        viewModelScope.launch {
            when (usersRepository.fetchAll().find { it.email == email }) {
                null -> _errorFlow.emit("Неверный email")
                else -> {
                    _emailCheckFlow.emit(true)
                    _emailFlow.emit(email)
                    _errorFlow.emit(null)
                }
            }
        }
    }

    private fun updateUser(user: User) {
        viewModelScope.launch { usersRepository.update(user) }
    }

}