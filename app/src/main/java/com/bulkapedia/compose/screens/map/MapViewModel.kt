package com.bulkapedia.compose.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.maps.Map
import com.bulkapedia.compose.data.repos.firestore.Repository
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapsRepository: Repository<Map>
) : ViewModel() {

    private val _mapFlow: MutableStateFlow<Map?> = MutableStateFlow(null)
    val mapFlow: StateFlow<Map?> = _mapFlow

    private var listener: ListenerRegistration? = null

    fun fetchMap(mapImage: String) {
        listener = mapsRepository.fetchAll { allMaps ->
            viewModelScope.launch { _mapFlow.emit(allMaps.find { it.id == mapImage }) }
        }
    }

    fun dispose() = listener?.remove()

}