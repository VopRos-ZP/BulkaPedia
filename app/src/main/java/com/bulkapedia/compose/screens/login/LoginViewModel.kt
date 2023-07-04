package com.bulkapedia.compose.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.data.users.User
import com.bulkapedia.data.users.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _errorFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorFlow: StateFlow<String?> = _errorFlow

    fun login(email: String, password: String, onSuccess: (User) -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            usersRepository.login(email, password, onSuccess) {
                viewModelScope.launch { _errorFlow.emit(it) }
            }
        } else {
            viewModelScope.launch {
                _errorFlow.emit("Логин и/или пароль не должны быть пустыми")
            }
        }
    }

}