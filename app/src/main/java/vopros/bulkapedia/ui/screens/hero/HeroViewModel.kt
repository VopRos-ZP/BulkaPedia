package vopros.bulkapedia.ui.screens.hero

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ListenerRegistration
import vopros.bulkapedia.core.Callback
import vopros.bulkapedia.hero.Hero
import vopros.bulkapedia.hero.HeroRepository
import vopros.bulkapedia.ui.view.IntentViewModel
import vopros.bulkapedia.ui.view.Reducer
import vopros.bulkapedia.user.UserRepository
import vopros.bulkapedia.userSet.SetRepository
import vopros.bulkapedia.userSet.UserSet
import vopros.bulkapedia.userSet.UserSetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val setRepository: SetRepository,
    private val userRepository: UserRepository,
): IntentViewModel<HeroViewIntent>() {

//    private val _hero = MutableStateFlow<Hero?>(null)
//    private val _sets = MutableStateFlow<List<UserSet>>(emptyList())
//
//    private var setsListener: ListenerRegistration? = null
//    private var heroListener: ListenerRegistration? = null
//
//    override var reducer: Reducer<HeroViewIntent> = Reducer { intent, _ ->
//        when (intent) {
//            is HeroViewIntent.Fetch -> fetch(intent.heroId)
//            is HeroViewIntent.Dispose -> onDispose()
//        }
//    }
//
//    private suspend fun fetch(heroId: String) {
//        setsListener = setRepository.listenAll(Callback(this::error) {
//            viewModelScope.launch {
//                val sets = it
//                    .filter { s -> s.hero == heroId }
//                    .sortedByDescending { s -> s.liked.count() }
//                    .take(3)
//                _sets.emit(sets)
//            }
//        })
//        heroListener = heroRepository.listenOne(heroId, Callback(this::error) {
//            viewModelScope.launch { _hero.emit(it) }
//        })
//        _hero
//            .combine(_sets) { hero, sets -> Pair(hero, sets) }
//            .collect { (hero, sets) ->
//                if (hero != null) {
//                    success(Pair(hero, sets.map {
//                        UserSetUseCase(it,
//                            userRepository.fetchOne(it.author),
//                            heroRepository.fetchOne(it.hero)
//                        )
//                    }))
//                }
//            }
//    }
//
//    private fun onDispose() {
//        setsListener?.remove()
//        heroListener?.remove()
//    }

}