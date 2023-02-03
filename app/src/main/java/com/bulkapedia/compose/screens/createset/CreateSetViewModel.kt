package com.bulkapedia.compose.screens.createset

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.events.EventHandler
import com.bulkapedia.compose.data.heroes.Hero
import com.bulkapedia.compose.data.sets.GearCell
import com.bulkapedia.compose.data.sets.UserSet
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class CreateSetEvent {
    data class LoadContent(val author: String, val heroId: String, val setId: String): CreateSetEvent()
    data class SaveSet(val set: UserSet): CreateSetEvent()
}

sealed class CreateSetViewState {
    object Loading: CreateSetViewState()
    data class Enter(val hero: Hero, val set: UserSet): CreateSetViewState()
    data class Error(val message: String): CreateSetViewState()
}

val emptyGears = mapOf(
    GearCell.HEAD to "empty_head",
    GearCell.BODY to "empty_body",
    GearCell.ARM to "empty_arm",
    GearCell.LEG to "empty_leg",
    GearCell.DECOR to "empty_decor",
    GearCell.DEVICE to "empty_device",
)

val emptyIcons = listOf(
    "empty_head", "empty_body",
    "empty_arm", "empty_leg",
    "empty_decor", "empty_device",
)

@HiltViewModel
class CreateSetViewModel @Inject constructor() : ViewModel(), EventHandler<CreateSetEvent> {

    private val _liveData: MutableLiveData<CreateSetViewState> = MutableLiveData(CreateSetViewState.Loading)
    val liveData: LiveData<CreateSetViewState> = _liveData

    private var heroListener: ListenerRegistration? = null
    private var setListener: ListenerRegistration? = null

    override fun obtainEvent(event: CreateSetEvent) {
        when (val state = _liveData.value!!) {
            is CreateSetViewState.Loading -> reduce(event, state)
            is CreateSetViewState.Enter -> reduce(event, state)
            else -> {}
        }
    }

    private fun reduce(event: CreateSetEvent, state: CreateSetViewState.Loading) {
        when (event) {
            is CreateSetEvent.LoadContent -> fetchData(event.heroId, event.setId, event.author)
            is CreateSetEvent.SaveSet -> saveSet(event.set)
        }
    }

    private fun reduce(event: CreateSetEvent, state: CreateSetViewState.Enter) {
        when (event) {
            is CreateSetEvent.LoadContent -> fetchData(event.heroId, event.setId, event.author)
            is CreateSetEvent.SaveSet -> saveSet(event.set)
        }
    }

    private fun saveSet(set: UserSet) {
        Database().addSet(set)
    }

    private fun fetchData(heroId: String, setId: String, author: String) {
        val db = Database()
        if (setId.isEmpty()) {
            heroListener = db.addHeroesSnapshotListener { heroes ->
                val hero = heroes.find { it.heroId == heroId }
                if (hero != null) {
                    _liveData.postValue(CreateSetViewState.Enter(hero, UserSet(
                        "", author, heroId, emptyGears, 0, mutableListOf()
                    )
                    ))
                } else {
                    _liveData.postValue(CreateSetViewState.Error("Герой не найден"))
                }
            }
        } else {
            setListener = db.addSetsSnapshotListener { sets ->
                val current = sets.find { it.userSetId == setId }!!
                heroListener = db.addHeroesSnapshotListener { heroes ->
                    val hero = heroes.find { it.heroId == heroId }
                    if (hero != null) {
                        _liveData.postValue(CreateSetViewState.Enter(hero, current))
                    } else {
                        _liveData.postValue(CreateSetViewState.Error("Герой не найден"))
                    }
                }
            }
        }
    }

    fun removeListeners() {
        heroListener?.remove()
        setListener?.remove()
    }
}
