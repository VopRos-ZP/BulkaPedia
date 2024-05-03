package ru.bulkapedia.presentation.ui.screens.map.component

import ru.bulkapedia.presentation.ui.screens.map.mvi.MapStore.State
import kotlinx.coroutines.flow.StateFlow

interface MapComponent {

    val state: StateFlow<State>

    fun toggleShowSpawns()
    fun onBackClick()

}