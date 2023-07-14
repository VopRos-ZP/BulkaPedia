package com.bulkapedia.compose.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bulkapedia.Callback
import bulkapedia.StoreRepository
import bulkapedia.maps.GameMap
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapsRepository: StoreRepository<GameMap>
) : ViewModel() {

    private val _mapFlow: MutableStateFlow<GameMap?> = MutableStateFlow(null)
    val mapFlow: StateFlow<GameMap?> = _mapFlow

    private var listener: ListenerRegistration? = null

    fun fetchMap(mapImage: String) {
        listener = mapsRepository.listenAll(Callback({ allMaps ->
            viewModelScope.launch { _mapFlow.emit(allMaps.find { it.mapId == mapImage }) }
        }))
    }

    fun dispose() = listener?.remove()

}