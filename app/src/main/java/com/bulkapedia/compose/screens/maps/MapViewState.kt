package com.bulkapedia.compose.screens.maps

import androidx.lifecycle.*
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.events.EventHandler
import com.bulkapedia.compose.data.Firestore
import kotlinx.coroutines.launch
import com.bulkapedia.compose.data.Map
import com.bulkapedia.compose.models.TagViewState
import com.bulkapedia.compose.util.SortType
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class MapsViewEvent {
    object LoadAllMaps: MapsViewEvent()
    object RemoveAllMaps: MapsViewEvent()
    data class OnTagClick(val tagState: TagViewState): MapsViewEvent()
}

sealed class MapViewState {
    object StateLoading: MapViewState()
    object StateNoItem: MapViewState()
    data class StateData(val map: Map): MapViewState()
}

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel(), EventHandler<MapsEvent> {

    private val _viewState: MutableLiveData<MapViewState> = MutableLiveData(MapViewState.StateLoading)
    val viewState: LiveData<MapViewState> = _viewState

    override fun obtainEvent(event: MapsEvent) {
        when (val currentState = _viewState.value!!) {
            is MapViewState.StateLoading -> reduce(event, currentState)
            is MapViewState.StateData -> reduce(event, currentState)
            is MapViewState.StateNoItem -> reduce(event, currentState)
        }
    }

    private fun reduce(event: MapsEvent, currentState: MapViewState.StateLoading) {
        when (event) {
            MapsEvent.EnterScreen -> fetchMaps()
            is MapsEvent.OnMapClick -> fetchMaps(mapId = event.mapId)
            else -> notImplError(event, currentState)
        }
    }

    private fun reduce(event: MapsEvent, currentState: MapViewState.StateData) {
        when (event) {
            MapsEvent.EnterScreen -> fetchMaps()
            is MapsEvent.OnMapClick -> fetchMaps(mapId = event.mapId)
            else -> notImplError(event, currentState)
        }
    }

    private fun reduce(event: MapsEvent, currentState: MapViewState.StateNoItem) {
        when (event) {
            MapsEvent.EnterScreen -> fetchMaps(isLoading = true)
            else -> notImplError(event, currentState)
        }
    }

    private fun notImplError(event: MapsEvent, currentState: MapViewState) {
        throw NotImplementedError("Invalid $event for state $currentState")
    }

    private fun fetchMaps(isLoading: Boolean = false, mapId: String = "") {
        viewModelScope.launch {
            if (isLoading || mapId.isEmpty()) {
                _viewState.postValue(MapViewState.StateLoading)
            } else {
                val maps = Firestore.Maps.getMaps()
                if (maps != null) {
                    val data = maps.find { it.mapId == mapId }
                    if (maps.isEmpty() || data == null) {
                        _viewState.postValue(MapViewState.StateNoItem)
                    } else {
                        _viewState.postValue(MapViewState.StateData(data))
                    }
                }
            }
        }
    }

}

@HiltViewModel
class MapsViewModel @Inject constructor() : ViewModel(), EventHandler<MapsViewEvent> {

    val liveData: MutableLiveData<List<Map>?> = MutableLiveData(emptyList())

    private var listener: ListenerRegistration? = null

    private fun listenMaps() {
        listener = Database().addMapsSnapshotListener(liveData::postValue)
    }

    private fun removeListener() {
        listener?.remove()
    }

    override fun obtainEvent(event: MapsViewEvent) {
        when(event) {
            MapsViewEvent.LoadAllMaps -> listenMaps()
            MapsViewEvent.RemoveAllMaps -> removeListener()
            is MapsViewEvent.OnTagClick -> filterMaps(event.tagState)
        }
    }

    private fun filterMaps(tagState: TagViewState) {
        if (tagState.selected) {
            if (tagState.sortType is SortType.ByMap) {
                val maps = liveData.value?.filter { it.mode == tagState.sortType.type.str() }
                liveData.postValue(maps)
            }
        }
    }

}
