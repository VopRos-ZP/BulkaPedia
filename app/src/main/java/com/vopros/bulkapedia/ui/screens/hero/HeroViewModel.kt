package com.vopros.bulkapedia.ui.screens.hero

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ListenerRegistration
import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.hero.Hero
import com.vopros.bulkapedia.hero.HeroRepository
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
import com.vopros.bulkapedia.ui.view.ViewState
import com.vopros.bulkapedia.user.UserRepository
import com.vopros.bulkapedia.userSet.SetRepository
import com.vopros.bulkapedia.userSet.UserSet
import com.vopros.bulkapedia.userSet.UserSetWithUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val setRepository: SetRepository,
    private val userRepository: UserRepository
): IntentViewModel<HeroViewIntent>() {

    private val _hero = MutableStateFlow<Hero?>(null)
    private val _sets = MutableStateFlow<List<UserSet>>(emptyList())

    private var setsListener: ListenerRegistration? = null
    private var heroListener: ListenerRegistration? = null

    override var reducer: Reducer<HeroViewIntent> = Reducer { intent, _ ->
        when (intent) {
            is HeroViewIntent.Fetch -> fetch(intent.heroId)
            is HeroViewIntent.Dispose -> onDispose()
        }
    }

    private suspend fun fetch(heroId: String) {
        val onError: (String) -> Unit = { it ->
            viewModelScope.launch { innerState.emit(ViewState.Error(it)) }
        }
        try {
            setsListener = setRepository.listenAll(Callback(onError) {
                viewModelScope.launch {
                    val sets = it
                        .filter { s -> s.hero == heroId }
                        .sortedByDescending { s -> s.liked.count() }
                        .take(3)
                    _sets.emit(sets)
                }
            })
            heroListener = heroRepository.listenOne(heroId, Callback(onError) {
                viewModelScope.launch { _hero.emit(it) }
            })
            _hero.combine(_sets) { hero, sets -> Pair(hero, sets) }
                .collect { (hero, sets) ->
                    if (hero != null) {
                        innerState.emit(ViewState.Success(Pair(hero, sets.map {
                            UserSetWithUser(
                                it.id, userRepository.fetchOne(it.author),
                                it.gears, it.hero, it.liked
                            )
                        })))
                    }
                }
        } catch (e: RuntimeException) {
            innerState.emit(ViewState.Error(e.message ?: "Runtime exception"))
        }
    }

    private fun onDispose() {
        setsListener?.remove()
        heroListener?.remove()
    }

}