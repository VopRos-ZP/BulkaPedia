package ru.bulkapedia.presentation.screens.root

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class RootViewModel(

) : ViewModel() {

    private val _state = MutableStateFlow(RootViewState())
    val state = _state.asStateFlow()

}