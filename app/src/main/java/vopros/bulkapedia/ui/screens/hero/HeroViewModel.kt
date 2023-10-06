package vopros.bulkapedia.ui.screens.hero

import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import vopros.bulkapedia.core.Callback
import vopros.bulkapedia.hero.Hero
import vopros.bulkapedia.hero.HeroRepository
import vopros.bulkapedia.ui.view.ErrViewModel
import vopros.bulkapedia.user.UserRepository
import vopros.bulkapedia.userSet.SetRepository
import vopros.bulkapedia.userSet.UserSetUseCase
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val setRepository: SetRepository,
    private val userRepository: UserRepository
): ErrViewModel() {

    private val _hero = MutableStateFlow<Hero?>(null)
    val hero = _hero.asStateFlow()

    private val _sets = MutableStateFlow<List<UserSetUseCase>>(emptyList())
    val sets = _sets.asStateFlow()

    private var setsListener: ListenerRegistration? = null
    private var heroListener: ListenerRegistration? = null

    fun fetch(heroId: String) {
        heroListener = heroRepository.listenOne(heroId, Callback(::error) {
            coroutine { _hero.emit(it) }
        })
        setsListener = setRepository.listenAll(Callback(::error) {
            coroutine {
                val top3 = it
                    .filter { s -> s.hero == heroId }
                    .sortedByDescending { s -> s.liked.count() }
                    .take(3)
                    .map { UserSetUseCase(it,
                        userRepository.fetchOne(it.author),
                        heroRepository.fetchOne(it.hero))
                    }
                _sets.emit(top3)
            }
        })
    }

    fun dispose() {
        heroListener?.remove()
        setsListener?.remove()
    }

}