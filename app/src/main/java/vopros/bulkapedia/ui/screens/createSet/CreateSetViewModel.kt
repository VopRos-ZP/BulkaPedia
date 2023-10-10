package vopros.bulkapedia.ui.screens.createSet

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import vopros.bulkapedia.hero.HeroRepository
import vopros.bulkapedia.storage.DataStore
import vopros.bulkapedia.ui.view.ErrViewModel
import vopros.bulkapedia.user.UserRepository
import vopros.bulkapedia.userSet.SetRepository
import vopros.bulkapedia.userSet.UserSet
import vopros.bulkapedia.userSet.UserSetUseCase
import javax.inject.Inject

@HiltViewModel
class CreateSetViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val setRepository: SetRepository,
    private val userRepository: UserRepository,
    private val heroRepository: HeroRepository,
) : ErrViewModel() {

    private val _useCase = MutableStateFlow<UserSetUseCase?>(null)
    val useCase = _useCase.asStateFlow()

    fun init(heroId: String, setId: String?) {
        coroutine {
            dataStore.userId.collect { token ->
                _useCase.emit(UserSetUseCase(
                    user = userRepository.fetchOne(token),
                    hero = heroRepository.fetchOne(heroId),
                    set = when (setId) {
                        null -> UserSet("", token, emptyMap(), heroId, emptyList())
                        else -> setRepository.fetchOne(setId)
                    }
                ))
            }
        }
    }

    fun saveSet(userSet: UserSet) {
        coroutine { setRepository.update(userSet) }
    }

}