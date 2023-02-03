package com.bulkapedia.compose.screens.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.events.EventHandler
import com.bulkapedia.compose.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class RegistrationViewState {
    object Loading: RegistrationViewState()
    data class Success(val user: User): RegistrationViewState()
    data class Error(val message: String): RegistrationViewState()
}

@HiltViewModel
class RegistrationViewModel @Inject constructor() : ViewModel(), EventHandler<RegistrationEvent> {

    private val _liveData: MutableLiveData<RegistrationViewState> = MutableLiveData(RegistrationViewState.Loading)
    val liveData: LiveData<RegistrationViewState> = _liveData

    override fun obtainEvent(event: RegistrationEvent) {
        when (val state = _liveData.value!!) {
            is RegistrationViewState.Loading -> reduce(event, state)
            is RegistrationViewState.Success -> reduce(event, state)
            is RegistrationViewState.Error -> reduce(event, state)
        }
    }

    private fun reduce(event: RegistrationEvent, state: RegistrationViewState.Loading) {
        when (event) {
            RegistrationEvent.EnterScreen -> registration {}
            is RegistrationEvent.OnSignUpClick -> registration(event.email, event.password, event.nickname, event.onSuccess)
            is RegistrationEvent.ErrorScreen -> registration {}
        }
    }

    private fun reduce(event: RegistrationEvent, state: RegistrationViewState.Success) {
        when (event) {
            RegistrationEvent.EnterScreen -> registration {}
            is RegistrationEvent.OnSignUpClick -> registration(event.email, event.password, event.nickname, event.onSuccess)
            is RegistrationEvent.ErrorScreen -> registration {}
        }
    }

    private fun reduce(event: RegistrationEvent, state: RegistrationViewState.Error) {
        when (event) {
            RegistrationEvent.EnterScreen -> registration {}
            is RegistrationEvent.OnSignUpClick -> registration(event.email, event.password, event.nickname, event.onSuccess)
            is RegistrationEvent.ErrorScreen -> registration {}
        }
    }

    private fun registration(email: String = "", password: String = "", nickname: String = "", onSuccess: () -> Unit) {
        viewModelScope.launch {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                try {
                    Database().signUp(email, password, nickname) {
                        onSuccess.invoke()
                        _liveData.postValue(RegistrationViewState.Success(it))
                    }
                } catch (e: Exception) {
                    _liveData.postValue(RegistrationViewState.Error(e.localizedMessage ?: ""))
                }
            } else {
                _liveData.postValue(RegistrationViewState.Error("Почта и/или пароль не должна/-ы быть путой/-ыми"))
            }
        }
    }

}