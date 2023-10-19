package vopros.bulkapedia.ui.screens.registration

import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import vopros.bulkapedia.firebase.AuthRepository
import vopros.bulkapedia.ui.view.ErrViewModel
import vopros.bulkapedia.user.User
import vopros.bulkapedia.user.UserRepository
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ErrViewModel() {

    fun register(
        login: String,
        password: String,
        nickname: String,
        onSuccess: () -> Unit
    ) {
        coroutine {
            authRepository.register(User(
                "", false,
                login, nickname, password,
                Timestamp.now(), Timestamp.now()
            ), ::error) { onSuccess() }
        }
    }

}