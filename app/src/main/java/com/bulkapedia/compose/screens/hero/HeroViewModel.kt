package com.bulkapedia.compose.screens.hero

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.heroes.Hero
import com.bulkapedia.compose.data.repos.heroes.HeroesRepository
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val heroesRepository: HeroesRepository,
    private val setsRepository: SetsRepository
): ViewModel() {

    private val _heroFlow: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val heroFlow: StateFlow<Hero?> = _heroFlow

    val setsFlow: SnapshotStateList<UserSet> = mutableStateListOf()

    private var heroListener: ListenerRegistration? = null
    private var setsListener: ListenerRegistration? = null

    fun fetchHero(heroId: String) {
        heroListener = heroesRepository.fetchAll { heroes ->
            viewModelScope.launch { _heroFlow.emit(heroes.find { it.id == heroId }) }
        }
        setsListener = setsRepository.fetchAll { allSets ->
            val heroSets = allSets.filter { it.hero == heroId }
                .sortedByDescending { it.userLikeIds.size }
                .take(3)
            setsFlow.clear()
            setsFlow.addAll(heroSets)
        }
    }

    fun deleteUserSet(set: UserSet) {
        setsRepository.delete(set)
    }

    fun dispose() {
        heroListener?.remove()
        setsListener?.remove()
    }

}