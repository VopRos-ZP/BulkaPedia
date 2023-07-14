package com.bulkapedia.compose.screens.login

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
class LoginViewModel @Inject constructor(
    private val usersRepository: UserRepository
) : ViewModel() {

    private val _errorFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorFlow: StateFlow<String?> = _errorFlow

    fun login(email: String, password: String, onSuccess: (User) -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModelScope.launch {
                usersRepository.login(email, password)
                usersRepository.fetchAll()
                    .first { it.email == email && it.password == password }
                    .apply(onSuccess)
            }
        } else {
            viewModelScope.launch {
                _errorFlow.emit("Логин и/или пароль не должны быть пустыми")
            }
        }
    }

}