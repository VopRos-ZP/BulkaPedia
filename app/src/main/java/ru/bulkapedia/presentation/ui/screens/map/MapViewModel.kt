package ru.bulkapedia.presentation.ui.screens.map

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.repository.MapsRepository
import javax.inject.Inject

object Map {
    data class State(
        val map: GameMap? = null,
        val isShowSpawns: Boolean = false,
        val error: String? = null
    )
}

@HiltViewModel
class MapViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val mapsRepository: MapsRepository
): ViewModel() {

    private val mapId: String = checkNotNull(savedStateHandle["id"])

    private val _state = MutableStateFlow(Map.State())
    val state = _state.asStateFlow()

    fun fetchMap() {
        viewModelScope.launch {
            mapsRepository.map(mapId).collect {
                _state.value = state.value.copy(map = it)
            }
        }
    }

    fun toggleShowSpawns() {
        _state.value = state.value.copy(isShowSpawns = !state.value.isShowSpawns)
    }

    fun closeError() {
        _state.value = state.value.copy(error = null)
    }

}