package com.bulkapedia.compose.screens.registration

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
class RegistrationViewModel @Inject constructor(
    private val usersRepository: UsersRepository
) : ViewModel() {

    private val _errorFlow: MutableStateFlow<String?> = MutableStateFlow(null)
    val errorFlow: StateFlow<String?> = _errorFlow

    fun registration(email: String, password: String, nickname: String, onSuccess: (User) -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            val user = User("", email, password, nickname)
            usersRepository.register(user, onSuccess)
        } else {
            viewModelScope.launch { _errorFlow.emit("Почта и/или пароль не должна/-ы быть путой/-ыми") }
        }
    }

}