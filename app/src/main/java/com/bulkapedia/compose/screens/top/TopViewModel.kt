package com.bulkapedia.compose.screens.top

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.events.EventHandler
import com.bulkapedia.compose.data.sets.UserSet
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor() : ViewModel(), EventHandler<TopEvent> {

    private val _liveData: MutableLiveData<TopViewState> = MutableLiveData(TopViewState.Loading)
    val liveData: LiveData<TopViewState> = _liveData
    val setState: MutableLiveData<UserSet?> = MutableLiveData(null)

    private var listener: ListenerRegistration? = null

    override fun obtainEvent(event: TopEvent) {
        when (val state = _liveData.value!!) {
            is TopViewState.Loading -> reduce(event, state)
            is TopViewState.EnterSets -> reduce(event, state)
            is TopViewState.Error -> reduce(event, state)
        }
    }

    private fun reduce(event: TopEvent, state: TopViewState.Error) {
        when (event) {
            is TopEvent.LoadingSets -> fetchSets(event.hero, event.state)
        }
    }

    private fun reduce(event: TopEvent, state: TopViewState.Loading) {
        when (event) {
            is TopEvent.LoadingSets -> fetchSets(event.hero, event.state)
        }
    }

    private fun reduce(event: TopEvent, state: TopViewState.EnterSets) {
        when (event) {
            is TopEvent.LoadingSets -> fetchSets(event.hero, event.state)
        }
    }

    private fun notImplError(event: TopEvent, state: TopViewState) {
        throw NotImplementedError("Invalid $event for state $state")
    }

    private fun fetchSets(hero: String, state: SnapshotStateList<UserSet>) {
        val id = hero.split("_")[0]
        Database().addSetsSnapshotListener { sets ->
            val filtered = sets
                .filter { it.hero == id }
                .sortedByDescending { it.likes }
                .take(100)
            state.clear()
            state.addAll(filtered)
            _liveData.postValue(TopViewState.EnterSets(state))
        }
    }

    fun listenSet(setId: String) {
        listener = Database().addSetsSnapshotListener { sets ->
            setState.postValue(sets.find { it.userSetId == setId })
        }
    }

    fun removeListener() {
        listener?.remove()
    }

}
