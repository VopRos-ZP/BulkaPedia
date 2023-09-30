package vopros.bulkapedia.ui.screens.profileController

import vopros.bulkapedia.storage.DataStore
import vopros.bulkapedia.ui.view.IntentViewModel
import vopros.bulkapedia.ui.view.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileControllerViewModel @Inject constructor(
    private val dataStore: DataStore
) : IntentViewModel<ProfileControllerViewIntent>() {

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
