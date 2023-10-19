package vopros.bulkapedia.ui.screens.registration

import com.google.firebase.Timestamp
import dagger.hilt.android.lifecycle.HiltViewModel
import vopros.bulkapedia.ui.view.ErrViewModel
import vopros.bulkapedia.user.User
import vopros.bulkapedia.user.UserRepository
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ErrViewModel() {



    fun register(
        login: String,
        password: String,
        nickname: String
    ) {
        coroutine {
            userRepository.update(User(
                "", false,
                login, nickname, password,
                Timestamp.now(), Timestamp.now()
            ))
        }
    }

}