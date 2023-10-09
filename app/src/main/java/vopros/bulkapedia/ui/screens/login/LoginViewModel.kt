package vopros.bulkapedia.ui.screens.login

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import vopros.bulkapedia.firebase.AuthRepository
import vopros.bulkapedia.storage.DataStore
import vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val authRepository: AuthRepository
): ErrViewModel() {

    private val _config = MutableStateFlow(Pair("", false))
    val config = _config.asStateFlow()

    fun login(email: String, password: String) {
        coroutine {
            authRepository.login(email, password, ::error) {
                coroutine { dataStore.saveData(it.id, true) }
            }
        }
    }

}