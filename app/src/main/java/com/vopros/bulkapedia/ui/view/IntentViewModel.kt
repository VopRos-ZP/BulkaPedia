package com.vopros.bulkapedia.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class IntentViewModel<I>: ViewModel() {

    protected open lateinit var reducer: Reducer<I>

    private val innerState = MutableStateFlow<ViewState>(ViewState.Loading)
    val state = innerState.asStateFlow()

    protected fun <T> success(data: T) {
        viewModelScope.launch { innerState.emit(ViewState.Success(data)) }
    }

    protected fun error(msg: String) {
        viewModelScope.launch { innerState.emit(ViewState.Error(msg)) }
    }

    fun startIntent(intent: I) {
        viewModelScope.launch {
            try { reducer.execute(intent, state.value) }
            catch (e: Exception) { e.localizedMessage?.let { error(it) } }
        }
    }

}