package vopros.bulkapedia.ui.screens.login

import androidx.lifecycle.viewModelScope
import vopros.bulkapedia.firebase.AuthRepository
import vopros.bulkapedia.storage.DataStore

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val authRepository: AuthRepository
): ErrViewModel() {

//    override var reducer: Reducer<LoginViewIntent> = Reducer { intent, _ ->
//        when (intent) {
//            is LoginViewIntent.Start -> init()
//            is LoginViewIntent.Login -> login(intent.email, intent.password)
//        }
//    }
//
//    private suspend fun init() {
//        dataStore.config.collect { success(it) }
//    }
//
//    private suspend fun login(email: String, password: String) {
//        authRepository.login(email, password, this::error) { user ->
//            viewModelScope.launch {
//                dataStore.saveData(user.id, true)
//                init() /* call collect config from DataStore */
//            }
//        }
//    }

}