package ru.bulkapedia.presentation.ui.screens.map.mvi

import com.arkivanov.mvikotlin.core.store.Store
import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.presentation.ui.screens.map.mvi.MapStore.*

interface MapStore : Store<Intent, State, Label> {

    data class State(
        val map: GameMap,
        val isShowSpawns: Boolean = false,
        val error: String? = null
    )

    sealed interface Intent {
        data object ToggleShowSpawns : Intent
        data object CloseError : Intent
    }

    sealed interface Label

}