package vopros.bulkapedia.ui.screens.profileController

import vopros.bulkapedia.storage.DataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileControllerViewModel @Inject constructor(
    private val dataStore: DataStore
): ErrViewModel() {

    private val _config = MutableStateFlow(Pair("", false))
    val config = _config.asStateFlow()

    fun fetchConfig() {
        coroutine { dataStore.config.collect { _config.emit(it) } }
    }

}
