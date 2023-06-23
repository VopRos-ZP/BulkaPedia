package com.bulkapedia.compose.screens.sets

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.events.EventHandler
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class SetTabViewState {
    object Loading: SetTabViewState()
    data class Enter(val state: SnapshotStateList<UserSet>): SetTabViewState()
    data class Error(val message: String): SetTabViewState()
}

sealed class HeroTabEvent {
    data class LoadingData(val hero: String, val setsState: SnapshotStateList<UserSet>): HeroTabEvent()
}

@HiltViewModel
class SetTabViewModel @Inject constructor() : ViewModel(), EventHandler<HeroTabEvent> {

    private val _liveData: MutableLiveData<SetTabViewState> = MutableLiveData(SetTabViewState.Loading)
    val liveData: LiveData<SetTabViewState> = _liveData

    val current: MutableLiveData<UserSet?> = MutableLiveData()

    private var setsListener: ListenerRegistration? = null

    fun removeListener() {
        setsListener?.remove()
    }

    override fun obtainEvent(event: HeroTabEvent) {
        when (val state = _liveData.value!!) {
            is SetTabViewState.Loading -> reduce(event, state)
            is SetTabViewState.Enter -> {}
            is SetTabViewState.Error -> {}
        }
    }

    private fun reduce(event: HeroTabEvent, state: SetTabViewState.Loading) {
        when (event) {
            is HeroTabEvent.LoadingData -> fetchSets(event.hero, event.setsState)
        }
    }

    private fun reduce(event: HeroTabEvent, state: SetTabViewState.Enter) {
        when (event) {
            is HeroTabEvent.LoadingData -> fetchSets(event.hero, event.setsState)
        }
    }

    private fun reduce(event: HeroTabEvent, state: SetTabViewState.Error) {
        when (event) {
            is HeroTabEvent.LoadingData -> fetchSets(event.hero, event.setsState)
        }
    }

    private fun fetchSets(hero: String, setsState: SnapshotStateList<UserSet>) {

    }

}