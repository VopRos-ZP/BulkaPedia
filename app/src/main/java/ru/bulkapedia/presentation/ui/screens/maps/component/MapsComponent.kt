package ru.bulkapedia.presentation.ui.screens.maps.component

import kotlinx.coroutines.flow.StateFlow
import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.model.MapMode
import ru.bulkapedia.presentation.ui.screens.maps.mvi.Maps

interface MapsComponent {

    val state: StateFlow<Maps.State>

    fun onNavigationBackClick()
    fun onMapModeSelected(mode: MapMode?)
    fun onGameMapClicked(map: GameMap)
    fun onCloseError()
}