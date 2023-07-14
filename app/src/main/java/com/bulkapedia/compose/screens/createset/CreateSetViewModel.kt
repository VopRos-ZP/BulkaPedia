package com.bulkapedia.compose.screens.createset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bulkapedia.Callback
import bulkapedia.StoreRepository
import bulkapedia.gears.Gear
import bulkapedia.heroes.Hero
import bulkapedia.gears.GearCell
import bulkapedia.sets.UserSet
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateSetViewModel @Inject constructor(
    private val setsRepository: StoreRepository<UserSet>,
    private val heroesRepository: StoreRepository<Hero>,
    private val gearsRepository: StoreRepository<Gear>
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
        heroListener = heroesRepository.listenAll(Callback({ heroes ->
            viewModelScope.launch { _heroFlow.emit(heroes.find { it.heroId == heroId }) }
        }))
        setListener = setsRepository.listenAll(Callback({ sets ->
            viewModelScope.launch {
                val set = sets.find { it.setId == setId } ?: UserSet.EMPTY
                isEdit = set != UserSet.EMPTY
                _setFlow.emit(set)
            }
        }))
    }

    fun fetchGears(set: UserSet) {
        gearsListener = gearsRepository.listenAll(Callback({ allGears ->
            val map = mutableMapOf<GearCell, Gear>()
            set.gears.forEach { (cell, icon) ->
                map[cell] = allGears.find { it.icon == icon } ?: Gear.EMPTY(cell)
            }
            viewModelScope.launch { gearsFlow.emit(map) }
        }))
    }

    fun removeListeners() {
        heroListener?.remove()
        setListener?.remove()
        gearsListener?.remove()
    }
}
