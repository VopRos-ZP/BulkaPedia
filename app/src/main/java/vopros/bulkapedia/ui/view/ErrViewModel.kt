package vopros.bulkapedia.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class ErrViewModel : ViewModel() {

    private val _error = MutableStateFlow("")
    val error = _error.asStateFlow()

    protected val listeners = mutableMapOf<String, ListenerRegistration>()

    private val errorHandler = CoroutineExceptionHandler { _, err ->
        viewModelScope.launch { error(err.localizedMessage ?: "") }
    }

    protected fun error(message: String = "") {
        viewModelScope.launch{ _error.emit(message) }
    }

    fun closeError() {
        coroutine { _error.emit("") }
    }

    protected fun coroutine(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(errorHandler, block = block)
    }

    open fun onDispose() {
        listeners.forEach { it.value.remove() }
    }

}