package vopros.bulkapedia.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class ErrViewModel : ViewModel() {

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    protected val errorHandler = CoroutineExceptionHandler { _, err ->
        viewModelScope.launch { error(err.localizedMessage ?: "") }
    }

    protected fun error(message: String = "") {
        viewModelScope.launch{ _error.emit(message) }
    }

    protected fun coroutine(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(errorHandler, block = block)
    }

}