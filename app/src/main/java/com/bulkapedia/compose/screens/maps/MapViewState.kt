package com.bulkapedia.compose.screens.maps

import androidx.lifecycle.*
import com.bulkapedia.compose.events.EventHandler
import com.bulkapedia.compose.data.Firestore
import kotlinx.coroutines.launch
import com.bulkapedia.compose.data.Map
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class MapViewState {
    object StateLoading: MapViewState()
    object StateNoItem: MapViewState()
    data class StateData(val map: Map<Any?, Any?>): MapViewState()
}

@HiltViewModel
class MapViewModel @Inject constructor() : ViewModel(), EventHandler<MapsEvent> {

    private val _viewState: MutableLiveData<MapViewState> = MutableLiveData(MapViewState.StateLoading)
    val viewState: LiveData<MapViewState> = _viewState

    var mapImage: String = ""

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
            else -> notImplError(event, currentState)
        }
    }

    private fun reduce(event: MapsEvent, currentState: MapViewState.StateData) {
        when (event) {
            MapsEvent.EnterScreen -> fetchMaps()
            is MapsEvent.OnMapClick -> fetchMaps()
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

    private fun fetchMaps(isLoading: Boolean = false) {
        viewModelScope.launch {
            if (isLoading) {
                _viewState.postValue(MapViewState.StateLoading)
            } else {
                val maps = Firestore.Maps.getMaps()
                if (maps != null) {
                    val data = maps.find { it.image == mapImage }
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
