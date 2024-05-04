package ru.bulkapedia.presentation.ui.screens.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.MapMode
import ru.bulkapedia.domain.repository.MapsRepository
import ru.bulkapedia.presentation.ui.screens.maps.mvi.Maps
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val mapsRepository: MapsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(Maps.State())
    val  state = _state.asStateFlow()

    fun fetchMaps() {
        viewModelScope.launch {
            mapsRepository.maps.collect {
                _state.value = state.value.copy(maps = it)
            }
        }
    }

    fun filterMaps(mapMode: MapMode?) {
        _state.value = state.value.copy(selectedMode = mapMode)
    }

    fun closeError() {
        _state.value = state.value.copy(error = null)
    }

}