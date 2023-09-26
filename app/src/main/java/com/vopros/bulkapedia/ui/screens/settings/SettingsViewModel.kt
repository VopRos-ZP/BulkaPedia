package com.vopros.bulkapedia.ui.screens.settings

import com.vopros.bulkapedia.firebase.AuthRepository
import com.vopros.bulkapedia.storage.DataStore
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
import com.vopros.bulkapedia.ui.view.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val authRepository: AuthRepository
): IntentViewModel<SettingsViewIntent>() {

    override var reducer: Reducer<SettingsViewIntent> = Reducer { intent, _ ->
        when (intent) {
            is SettingsViewIntent.Start -> init()
            is SettingsViewIntent.Logout -> logout()
        }
    }

    private suspend fun init() {
        dataStore.config.collect { innerState.emit(ViewState.Success(it)) }
    }

    private suspend fun logout() {
        authRepository.logout()
        dataStore.saveIsSign(false)
    }

}