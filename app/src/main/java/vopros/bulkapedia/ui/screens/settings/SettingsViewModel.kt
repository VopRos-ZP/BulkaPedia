package vopros.bulkapedia.ui.screens.settings

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import vopros.bulkapedia.firebase.AuthRepository
import vopros.bulkapedia.storage.DataStore
import vopros.bulkapedia.ui.view.ErrViewModel
import vopros.bulkapedia.user.User
import vopros.bulkapedia.user.UserRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository
): ErrViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    fun init() {
        coroutine { dataStore.config.collect { (token, isSign) ->
            if (isSign) {
                _user.emit(userRepository.fetchOne(token))
            }
        } }
    }

    fun logout() {
        authRepository.logout()
        coroutine { dataStore.saveIsSign(false) }
    }

}