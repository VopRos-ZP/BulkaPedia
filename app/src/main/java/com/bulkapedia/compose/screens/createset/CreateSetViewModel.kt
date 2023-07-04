package com.bulkapedia.compose.screens.createset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.data.CallBack
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.data.sets.UserSet
import com.bulkapedia.domain.gears.GearRepository
import com.bulkapedia.domain.heroes.HeroRepository
import com.bulkapedia.domain.sets.UserSetRepository
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateSetViewModel @Inject constructor(
    private val setsRepository: UserSetRepository,
    private val heroesRepository: HeroRepository,
    private val gearsRepository: GearRepository
) : ViewModel() {

    private val _heroFlow: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val heroFlow: StateFlow<Hero?> = _heroFlow

    private val _setFlow: MutableStateFlow<UserSet> = MutableStateFlow(UserSet.EMPTY)
    val setFlow: StateFlow<UserSet> = _setFlow

    val gearsFlow: MutableStateFlow<Map<GearCell, Gear>> = MutableStateFlow(emptyMap())

    private var heroListener: ListenerRegistration? = null
    private var setListener: ListenerRegistration? = null
    private var gearsListener: ListenerRegistration? = null

    private var isEdit = false

    fun saveSet(set: UserSet) {
        viewModelScope.launch { when (isEdit) {
            true -> setsRepository.update(set)
            else -> setsRepository.create(set)
        } }
    }

    fun fetchData(heroId: String, setId: String) {
        heroListener = heroesRepository.fetchAll(CallBack({ heroes ->
            viewModelScope.launch { _heroFlow.emit(heroes.find { it.heroId == heroId }) }
        }) {})
        setListener = setsRepository.fetchAll(CallBack({ sets ->
            viewModelScope.launch {
                val set = sets.find { it.userSetId == setId } ?: UserSet.EMPTY
                isEdit = set != UserSet.EMPTY
                _setFlow.emit(set)
            }
        }) {})
    }

    fun fetchGears(set: UserSet) {
        gearsListener = gearsRepository.fetchAll(CallBack({ allGears ->
            val map = mutableMapOf<GearCell, Gear>()
            set.gears.forEach { (cell, icon) ->
                map[cell] = allGears.find { it.icon == icon } ?: Gear.EMPTY(cell)
            }
            viewModelScope.launch { gearsFlow.emit(map) }
        }) {})
    }

    fun removeListeners() {
        heroListener?.remove()
        setListener?.remove()
        gearsListener?.remove()
    }
}
