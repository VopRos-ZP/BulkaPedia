package vopros.bulkapedia.ui.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class IntentViewModel<I, >: ViewModel() {

    protected open lateinit var reducer: Reducer<I>



    fun startIntent(intent: I) {
        viewModelScope.launch {
//            try { reducer.execute(intent, state.value) }
//            catch (e: Exception) { e.localizedMessage?.let { error(it) } }
        }
    }

}