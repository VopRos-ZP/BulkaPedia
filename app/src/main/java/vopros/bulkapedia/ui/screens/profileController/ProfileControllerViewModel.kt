package vopros.bulkapedia.ui.screens.profileController

import vopros.bulkapedia.storage.DataStore

import dagger.hilt.android.lifecycle.HiltViewModel
import vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileControllerViewModel @Inject constructor(
    private val dataStore: DataStore
): ErrViewModel() {

//    override var reducer: Reducer<ProfileControllerViewIntent> = Reducer { intent, _ ->
//        when (intent) {
//            is ProfileControllerViewIntent.Start -> init()
//        }
//    }
//
//    private suspend fun init() {
//        dataStore.config.collect { success(it) }
//    }

}
