package com.bulkapedia.compose.screens.hero

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.data.CallBack
import com.bulkapedia.data.Repository
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.data.sets.UserSet
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val heroesRepository: Repository<Hero>,
    private val setsRepository: Repository<UserSet>
): ViewModel() {

    private val _heroFlow: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val heroFlow: StateFlow<Hero?> = _heroFlow

    val setsFlow: SnapshotStateList<UserSet> = mutableStateListOf()

    private var heroListener: ListenerRegistration? = null
    private var setsListener: ListenerRegistration? = null

    fun fetchHero(heroId: String) {
        heroListener = heroesRepository.fetchAll(CallBack({ heroes ->
            viewModelScope.launch { _heroFlow.emit(heroes.find { it.heroId == heroId }) }
        }) {})
        setsListener = setsRepository.fetchAll(CallBack({ allSets ->
            val heroSets = allSets.filter { it.hero == heroId }
                .sortedByDescending { it.userLikeIds.size }
                .take(3)
            setsFlow.clear()
            setsFlow.addAll(heroSets)
        }) {})
    }

    fun deleteUserSet(set: UserSet) {
        viewModelScope.launch { setsRepository.delete(set) }
    }

    fun dispose() {
        heroListener?.remove()
        setsListener?.remove()
    }

}