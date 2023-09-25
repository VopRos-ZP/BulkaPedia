package com.vopros.bulkapedia.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class IntentViewModel<I>: ViewModel() {

    protected open lateinit var reducer: Reducer<I>

    private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
    val state = _state.asStateFlow()

    fun startIntent(intent: I) {
        viewModelScope.launch {
            _state.emit(reducer.reduce(intent, state.value))
        }
    }

}