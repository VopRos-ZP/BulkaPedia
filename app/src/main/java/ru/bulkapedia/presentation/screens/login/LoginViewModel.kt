package ru.bulkapedia.presentation.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(

) : ViewModel() {

    private val _state = MutableStateFlow(LoginViewState())
    val state = _state.asStateFlow()

    fun intent() {

    }

}