package com.vopros.bulkapedia.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class IntentViewModel<I>: ViewModel() {

    protected open lateinit var reducer: Reducer<I>

    protected val innerState = MutableStateFlow<ViewState>(ViewState.Loading)
    val state = innerState.asStateFlow()

    fun startIntent(intent: I) {
        viewModelScope.launch {
            reducer.execute(intent, state.value)
        }
    }

}