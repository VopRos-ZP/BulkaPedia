package com.bulkapedia.compose.screens.profile

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.events.EventHandler
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.data.Database
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class SetsProfileViewState {
    object Loading: SetsProfileViewState()
    data class EnterScreen (val sets: SnapshotStateList<UserSet>): SetsProfileViewState()
    data class ErrorScreen (val message: String): SetsProfileViewState()
}

sealed class SetsProfileEvent {
    data class OnProfileTabClick(val pred: (UserSet) -> Boolean, val setsState: SnapshotStateList<UserSet>): SetsProfileEvent()
}

@HiltViewModel
class SetsProfileViewModel @Inject constructor() : ViewModel(), EventHandler<SetsProfileEvent> {

    private val _liveData: MutableLiveData<SetsProfileViewState> = MutableLiveData(SetsProfileViewState.Loading)
    val liveData: LiveData<SetsProfileViewState> = _liveData

    private var listener: ListenerRegistration? = null

    override fun obtainEvent(event: SetsProfileEvent) {
        when (val state = _liveData.value!!) {
            is SetsProfileViewState.Loading -> reduce(event, state)
            is SetsProfileViewState.EnterScreen -> reduce(event, state)
            is SetsProfileViewState.ErrorScreen -> reduce(event, state)
        }
    }

    private fun reduce(event: SetsProfileEvent, state: SetsProfileViewState.Loading) {
        when (event) {
            is SetsProfileEvent.OnProfileTabClick -> fetchSets(false, event.pred, event.setsState)
        }
    }

    private fun reduce(event: SetsProfileEvent, state: SetsProfileViewState.EnterScreen) {
        when (event) {
            is SetsProfileEvent.OnProfileTabClick -> fetchSets(false, event.pred, event.setsState)
        }
    }

    private fun reduce(event: SetsProfileEvent, state: SetsProfileViewState.ErrorScreen) {
        when (event) {
            is SetsProfileEvent.OnProfileTabClick -> fetchSets(false, event.pred, event.setsState)
        }
    }

    private fun notImplError(event: SetsProfileEvent, state: SetsProfileViewState) {
        throw NotImplementedError("Invalid $event for state $state")
    }

    private fun fetchSets(
        isLoading: Boolean = false,
        predicate: ((UserSet) -> Boolean),
        state: SnapshotStateList<UserSet>
    ) {
        if (isLoading) {
            _liveData.postValue(SetsProfileViewState.Loading)
        } else {
            listener = Database().addSetsSnapshotListener { sets ->
                state.clear()
                state.addAll(sets.filter(predicate))
                _liveData.postValue(SetsProfileViewState.EnterScreen(state))
            }
        }
    }

    fun removeListener() {
        listener?.remove()
    }

}