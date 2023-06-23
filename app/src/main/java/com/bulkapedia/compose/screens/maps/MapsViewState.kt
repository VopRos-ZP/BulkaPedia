package com.bulkapedia.compose.screens.maps

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.bulkapedia.compose.data.repos.maps.Map
import com.bulkapedia.compose.data.repos.firestore.Repository
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val mapsRepository: Repository<Map>
) : ViewModel() {

    private val _mapsFlow: MutableStateFlow<List<Map>> = MutableStateFlow(emptyList())
    val mapsFlow: StateFlow<List<Map>> = _mapsFlow

    private var listener: ListenerRegistration? = null

    fun fetchMaps() {
        listener = mapsRepository.fetchAll {
            viewModelScope.launch { _mapsFlow.emit(it) }
        }
    }

    fun dispose() = listener?.remove()

}
