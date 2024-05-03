package ru.bulkapedia.presentation.ui.screens.maps.mvi

import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.model.MapMode

object Maps {

    data class State(
        val isLoading: Boolean = false,
        val maps: List<GameMap> = emptyList(),
        val error: String? = null,
        val selectedMode: MapMode? = null,
    )

    sealed interface Intent {
        data object OnCloseError : Intent
        data object OnNavigationBackClicked : Intent
        data class OnMapModeSelected(val mode: MapMode?) : Intent
        data class OnGameMapClicked(val map: GameMap) : Intent
    }

    sealed interface Label {
        data object NavigationBack : Label
        data class MapNavigation(val map: GameMap) : Label
    }

    sealed interface Msg {
        data class LoadingChanged(val value: Boolean) : Msg
        data class MapsChanged(val value: List<GameMap>) : Msg
        data class ErrorChanged(val value: String?) : Msg
        data class SelectedModeChanged(val value: MapMode?) : Msg
    }

    sealed interface Action {
        data class GameMapsChanged(val value: List<GameMap>) : Action
        data class ErrorChanged(val value: String?) : Action
    }

}