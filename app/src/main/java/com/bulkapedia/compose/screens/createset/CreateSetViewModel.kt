package com.bulkapedia.compose.screens.createset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.gears.Gear
import com.bulkapedia.compose.data.repos.gears.GearsRepository
import com.bulkapedia.compose.data.repos.heroes.Hero
import com.bulkapedia.compose.data.repos.heroes.HeroesRepository
import com.bulkapedia.compose.data.repos.sets.GearCell
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

val emptyIcons = listOf(
    "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Fhead%2Fempty_head.jpg?alt=media&token=ef3f8ac1-a217-4ff7-a0c1-1116d848e449",
    "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Fbody%2Fempty_body.jpg?alt=media&token=a740ee91-dd44-4801-bccf-09b02694dadb",
    "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Farm%2Fempty_arm.jpg?alt=media&token=b66f1a68-989a-4457-bed0-bb5c0d1c1573",
    "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Fleg%2Fempty_leg.jpg?alt=media&token=41887b2a-6756-4b00-8680-36c7a982dec4",
    "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Fdecor%2Fempty_decor.jpg?alt=media&token=71d82eb0-3d23-4784-8c65-c58ce934d3ea",
    "https://firebasestorage.googleapis.com/v0/b/bulkapedia-e46fe.appspot.com/o/gears%2Fdevice%2Fempty_device.jpg?alt=media&token=2166d17c-5896-4df5-9f26-93f221ff513b",
)

val emptyGears = mapOf(
    GearCell.HEAD to emptyIcons[0],
    GearCell.BODY to emptyIcons[1],
    GearCell.ARM to emptyIcons[2],
    GearCell.LEG to emptyIcons[3],
    GearCell.DECOR to emptyIcons[4],
    GearCell.DEVICE to emptyIcons[5],
)

@HiltViewModel
class CreateSetViewModel @Inject constructor(
    private val setsRepository: SetsRepository,
    private val heroesRepository: HeroesRepository,
    private val gearsRepository: GearsRepository
) : ViewModel() {

    private val _heroFlow: MutableStateFlow<Hero?> = MutableStateFlow(null)
    val heroFlow: StateFlow<Hero?> = _heroFlow

    private val _setFlow: MutableStateFlow<UserSet> = MutableStateFlow(UserSet.EMPTY)
    val setFlow: StateFlow<UserSet> = _setFlow

    val gearsFlow: MutableStateFlow<Map<GearCell, Gear>> = MutableStateFlow(emptyMap())

    private var heroListener: ListenerRegistration? = null
    private var setListener: ListenerRegistration? = null
    private var gearsListener: ListenerRegistration? = null

    fun saveSet(set: UserSet) {
        viewModelScope.launch { setsRepository.create(set) }
    }

    fun fetchData(heroId: String, setId: String, author: String) {
        heroListener = heroesRepository.fetchAll { heroes ->
            viewModelScope.launch { _heroFlow.emit(heroes.find { it.id == heroId }) }
        }
        setListener = setsRepository.fetchAll { sets ->
            viewModelScope.launch { _setFlow.emit(sets.find { it.id == setId } ?: UserSet.EMPTY) }
        }
    }

    fun fetchGears(set: UserSet) {
        gearsListener = gearsRepository.fetchAll { allGears ->
            val map = mutableMapOf<GearCell, Gear>()
            set.gears.forEach { (cell, icon) ->
                map[cell] = allGears.find { it.icon == icon } ?: Gear.EMPTY(cell)
            }
            viewModelScope.launch { gearsFlow.emit(map) }
        }
    }

    fun removeListeners() {
        heroListener?.remove()
        setListener?.remove()
        gearsListener?.remove()
    }
}
