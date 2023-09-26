package com.vopros.bulkapedia.ui.screens.login

import androidx.lifecycle.viewModelScope
import com.vopros.bulkapedia.firebase.AuthRepository
import com.vopros.bulkapedia.storage.DataStore
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
import com.vopros.bulkapedia.ui.view.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val authRepository: AuthRepository
): IntentViewModel<LoginViewIntent>() {

    override var reducer: Reducer<LoginViewIntent> = Reducer { intent, _ ->
        when (intent) {
            is LoginViewIntent.Start -> init()
            is LoginViewIntent.Login -> login(intent.email, intent.password)
        }
    }

    private suspend fun init() {
        dataStore.config.collect { innerState.emit(ViewState.Success(it)) }
    }

    private suspend fun login(email: String, password: String) {
        authRepository.login(email, password, this::onError) { user ->
            viewModelScope.launch {
                dataStore.saveData(user.id, true)
                init() /* call collect config from DataStore */
            }
        }
    }

}