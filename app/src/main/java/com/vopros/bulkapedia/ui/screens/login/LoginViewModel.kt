package com.vopros.bulkapedia.ui.screens.login

import com.vopros.bulkapedia.firebase.AuthRepository
import com.vopros.bulkapedia.storage.DataStore
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
import com.vopros.bulkapedia.ui.view.ViewState
import com.vopros.bulkapedia.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
): IntentViewModel<LoginViewIntent>() {

    override var reducer: Reducer<LoginViewIntent> = Reducer { intent, state ->
        when (intent) {
            is LoginViewIntent.Start -> init()
            is LoginViewIntent.Login -> login(intent.email, intent.password, intent.onSuccess)
        }
    }

    private suspend fun init() {
        dataStore.config.collect { (token, isSign) ->
            if (token.isNotEmpty() && isSign) {
                innerState.emit(ViewState.Success(userRepository.fetchOne(token)))
            }
        }
    }

    private fun login(
        email: String,
        password: String,
        onSuccess: suspend () -> Unit
    ) {

    }

}