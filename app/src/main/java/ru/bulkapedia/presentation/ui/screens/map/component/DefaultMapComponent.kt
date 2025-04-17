package ru.bulkapedia.presentation.ui.screens.map.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.presentation.ui.screens.map.mvi.MapStore
import ru.bulkapedia.presentation.ui.screens.map.mvi.MapStoreFactory

class DefaultMapComponent(
    private val storeFactory: MapStoreFactory,
    private val gameMap: GameMap,
    context: ComponentContext
) : MapComponent, ComponentContext by context {

    private val store = instanceKeeper.getStore { storeFactory.create(gameMap) }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<MapStore.State> = store.stateFlow

    override fun toggleShowSpawns() {
        store.accept(MapStore.Intent.ToggleShowSpawns)
    }

    override fun onBackClick() {
        store.accept(MapStore.Intent.CloseError)
    }

}