package com.bulkapedia.compose.screens.passwordreset

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.events.EventHandler
import com.bulkapedia.compose.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class PasswordResetViewState {
    object EnterScreen: PasswordResetViewState()
    data class CheckedEmail(val email: String): PasswordResetViewState()
    data class CheckedPassword(val user: User): PasswordResetViewState()
    data class Success(val updatedUser: User): PasswordResetViewState()
    data class Error(val message: String): PasswordResetViewState()
}

@HiltViewModel
class PasswordResetViewModel @Inject constructor() : ViewModel(), EventHandler<PasswordResetEvent> {

    private val _liveData: MutableLiveData<PasswordResetViewState> = MutableLiveData(PasswordResetViewState.EnterScreen)
    val liveData: LiveData<PasswordResetViewState> = _liveData

    override fun obtainEvent(event: PasswordResetEvent) {
        when (val state = _liveData.value!!) {
            is PasswordResetViewState.EnterScreen -> reduce(event, state)
            is PasswordResetViewState.Success -> reduce(event, state)
            is PasswordResetViewState.Error -> reduce(event, state)
            is PasswordResetViewState.CheckedEmail -> reduce(event, state)
            else -> {}
        }
    }

    private fun reduce(event: PasswordResetEvent, state: PasswordResetViewState.EnterScreen) {
        when (event) {
            PasswordResetEvent.NoEvent -> fetchUsers()
            is PasswordResetEvent.OnResetClick -> fetchUsers(event.email, event.newPassword)
            is PasswordResetEvent.CheckEmail -> checkEmail(event.email)
            is PasswordResetEvent.CheckNewPassword -> checkPassword(event.email, event.password, event.saveStore)
        }
    }

    private fun reduce(event: PasswordResetEvent, state: PasswordResetViewState.Success) {
        when (event) {
            PasswordResetEvent.NoEvent -> fetchUsers()
            is PasswordResetEvent.OnResetClick -> fetchUsers(event.email, event.newPassword)
            else -> {}
        }
    }

    private fun reduce(event: PasswordResetEvent, state: PasswordResetViewState.Error) {
        when (event) {
            PasswordResetEvent.NoEvent -> fetchUsers()
            is PasswordResetEvent.OnResetClick -> fetchUsers(event.email, event.newPassword)
            else -> {}
        }
    }

    private fun reduce(event: PasswordResetEvent, state: PasswordResetViewState.CheckedEmail) {
        when (event) {
            PasswordResetEvent.NoEvent -> fetchUsers()
            is PasswordResetEvent.OnResetClick -> fetchUsers(event.email, event.newPassword)
            is PasswordResetEvent.CheckNewPassword -> checkPassword(event.email, event.password, event.saveStore)
            else -> {}
        }
    }

    private fun checkPassword(email: String, password: String, save: (User) -> Unit) {
        Database().findUserByEmail(email, { error ->
            _liveData.postValue(PasswordResetViewState.Error(error))
        }) { _, user ->
            if (user.password == password) {
                save.invoke(user)
            } else {
                _liveData.postValue(PasswordResetViewState.Error(
                    "Пароли не совпадают, пожалуйста введите новый пароль"
                ))
            }
        }
    }

    private fun checkEmail(email: String) {
        val db = Database()
        db.findUserByEmail(email, { err ->
            _liveData.postValue(PasswordResetViewState.Error(err))
        }) { _, user ->
            db.sendPasswordResetEmail(user.email) {
                _liveData.postValue(PasswordResetViewState.CheckedEmail(user.email))
            }
        }
    }

    private fun fetchUsers(email: String = "", password: String = "") {
        if (email.isEmpty() && password.isEmpty()) {
            _liveData.postValue(PasswordResetViewState.EnterScreen)
        }
    }

}