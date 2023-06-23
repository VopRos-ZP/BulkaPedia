package com.bulkapedia.compose.screens.passwordreset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.database.User
import com.bulkapedia.compose.data.repos.database.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PasswordResetViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _emailFlow: MutableStateFlow<String> = MutableStateFlow("")
    val emailFlow: StateFlow<String> = _emailFlow

    private val _emailCheckFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val emailCheckFlow: StateFlow<Boolean> = _emailCheckFlow

    private val _errorFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorFlow: StateFlow<String?> = _errorFlow

    fun checkPassword(email: String, password: String, save: suspend (User) -> Unit) {
        usersRepository.findByEmail(email) { user ->
            user?.let {
                if (it.password == password) {
                    viewModelScope.launch {
                        _errorFlow.emit(null)
                        updateUser(it)
                        save(it)
                    }
                } else {
                    viewModelScope.launch { _errorFlow.emit("Пароли не совпадают, пожалуйста введите новый пароль") }
                }
            }
        }
    }

    fun checkEmail(email: String) {
        usersRepository.findByEmail(email) {
            if (it != null) {
                viewModelScope.launch {
                    _emailCheckFlow.emit(true)
                    _emailFlow.emit(email)
                    _errorFlow.emit(null)
                }
            } else {
                viewModelScope.launch { _errorFlow.emit("Неверный email") }
            }
        }
    }

    fun updateUser(user: User) {
        usersRepository.update(user) {}
    }

}