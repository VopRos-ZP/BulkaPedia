package com.bulkapedia.compose.screens.maps

import androidx.lifecycle.*
import bulkapedia.StoreRepository
import bulkapedia.maps.GameMap
import bulkapedia.Callback
import kotlinx.coroutines.launch
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val mapsRepository: StoreRepository<GameMap>
) : ViewModel() {

    private val _mapsFlow = MutableStateFlow<List<GameMap>>(emptyList())
    val mapsFlow = _mapsFlow.asStateFlow()

    private var listener: ListenerRegistration? = null

    fun fetchMaps() {
        listener = mapsRepository.listenAll(Callback({
            viewModelScope.launch { _mapsFlow.emit(it) }
        }))
    }

    fun dispose() = listener?.remove()

}
