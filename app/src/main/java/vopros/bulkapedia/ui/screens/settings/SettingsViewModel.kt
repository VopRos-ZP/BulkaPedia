package vopros.bulkapedia.ui.screens.settings

import vopros.bulkapedia.firebase.AuthRepository
import vopros.bulkapedia.storage.DataStore
import vopros.bulkapedia.ui.view.IntentViewModel
import vopros.bulkapedia.ui.view.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val authRepository: AuthRepository
): ErrViewModel() {

//    override var reducer: Reducer<SettingsViewIntent> = Reducer { intent, _ ->
//        when (intent) {
//            is SettingsViewIntent.Start -> init()
//            is SettingsViewIntent.Logout -> logout()
//        }
//    }
//
//    private suspend fun init() {
//        dataStore.config.collect { success(it) }
//    }
//
//    private suspend fun logout() {
//        authRepository.logout()
//        dataStore.saveIsSign(false)
//    }

}