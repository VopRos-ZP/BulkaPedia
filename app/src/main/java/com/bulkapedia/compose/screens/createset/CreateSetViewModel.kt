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
