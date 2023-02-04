package com.bulkapedia.compose.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.events.EventHandler
import com.bulkapedia.compose.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class LoginViewState {
    object Loading: LoginViewState()
    data class Error(val error: String): LoginViewState()
    data class Success(val user: User): LoginViewState()
}

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel(), EventHandler<LoginEvent> {

    private val _loginLiveData: MutableLiveData<LoginViewState> = MutableLiveData(LoginViewState.Loading)
    val loginLiveData: LiveData<LoginViewState> = _loginLiveData

    override fun obtainEvent(event: LoginEvent) {
        when (val state = _loginLiveData.value!!) {
            is LoginViewState.Loading -> reduce(event, state)
            is LoginViewState.Error -> reduce(event, state)
            is LoginViewState.Success -> reduce(event, state)
        }
    }

    private fun reduce(event: LoginEvent, state: LoginViewState.Loading) {
        when (event) {
            LoginEvent.EnterScreen -> getUsers(isLoading = true)
            is LoginEvent.ErrorScreen -> getUsers(isLoading = true)
            is LoginEvent.OnSignInClick -> getUsers(
                email = event.email,
                password = event.password,
                onSuccess = event.onSuccess
            )
            else -> notImplError(event, state)
        }
    }

    private fun reduce(event: LoginEvent, state: LoginViewState.Error) {
        when (event) {
            LoginEvent.EnterScreen -> getUsers(isLoading = true)
            else -> notImplError(event, state)
        }
    }

    private fun reduce(event: LoginEvent, state: LoginViewState.Success) {
        when (event) {
            LoginEvent.EnterScreen -> getUsers(isLoading = true)
            is LoginEvent.OnSignInClick -> getUsers(
                email = event.email,
                password = event.password,
                onSuccess = event.onSuccess
            )
            else -> notImplError(event, state)
        }
    }

    private fun notImplError(event: LoginEvent, state: LoginViewState) {
        throw NotImplementedError("Invalid $event for state $state")
    }

    private fun getUsers(
        isLoading: Boolean = false,
        email: String = "", password: String = "",
        onSuccess: (User) -> Unit = {}
    ) {
        val db = Database()
        if (isLoading) {
            _loginLiveData.postValue(LoginViewState.Loading)
        } else {
            if (password.isNotEmpty()) {
                db.signIn(email, password, { e ->
                    _loginLiveData.postValue(LoginViewState.Error(e))
                }) { user ->
                    onSuccess.invoke(user)
                    _loginLiveData.postValue(LoginViewState.Success(user))
                }
            } else {
                db.findUserByEmail(email, {
                    _loginLiveData.postValue(LoginViewState.Error(it))
                }) { _, user ->
                    onSuccess.invoke(user)
                    _loginLiveData.postValue(LoginViewState.Success(user))
                }
            }
        }
    }

}