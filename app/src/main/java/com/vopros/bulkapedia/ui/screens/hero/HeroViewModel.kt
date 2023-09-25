package com.vopros.bulkapedia.ui.screens.hero

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ListenerRegistration
import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.hero.Hero
import com.vopros.bulkapedia.hero.HeroRepository
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
import com.vopros.bulkapedia.ui.view.ViewState
import com.vopros.bulkapedia.userSet.SetRepository
import com.vopros.bulkapedia.userSet.UserSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
    private val setRepository: SetRepository
): IntentViewModel<HeroViewIntent>() {

    private var setsListener: ListenerRegistration? = null
    private var heroListener: ListenerRegistration? = null

    override var reducer: Reducer<HeroViewIntent> = Reducer { intent, state ->
        when (intent) {
            is HeroViewIntent.Fetch -> fetchOne(intent.heroId, state)
            is HeroViewIntent.Dispose -> {
                onDispose()
                state
            }
        }
    }

    private suspend fun fetchOne(heroId: String, state: ViewState): ViewState {
        val heroFlow = MutableStateFlow<Hero?>(null)
        val setsFlow = MutableStateFlow<List<UserSet>>(emptyList())
        val result = MutableStateFlow(state)
        return try {
            heroListener = heroRepository.listenOne(heroId, Callback({
                viewModelScope.launch { result.emit(ViewState.Error(it)) }
            }) {
                viewModelScope.launch { heroFlow.emit(it) }
            })
            setsListener = setRepository.listenAll(Callback({
                viewModelScope.launch { result.emit(ViewState.Error(it)) }
            }) {
                val sets = it
                    .filter { s -> s.hero == heroId }
                    .sortedBy { s -> s.liked.count() }
                    .take(3)
                viewModelScope.launch { setsFlow.emit(sets) }
            })

            if (heroFlow.value != null) {
                result.emit(ViewState.Success(Pair(heroFlow.value, setsFlow.value)))
            }
            result.value
        } catch (e: RuntimeException) {
            ViewState.Error(e.message ?: "Runtime exception")
        }
    }

    private fun onDispose() {
        setsListener?.remove()
        heroListener?.remove()
    }

}