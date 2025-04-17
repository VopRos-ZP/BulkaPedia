package ru.bulkapedia.presentation.ui.screens.maps.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.model.MapMode
import ru.bulkapedia.presentation.extensions.componentScope
import ru.bulkapedia.presentation.ui.screens.maps.mvi.Maps
import ru.bulkapedia.presentation.ui.screens.maps.mvi.MapsStore
import ru.bulkapedia.presentation.ui.screens.maps.mvi.MapsStoreFactory

class DefaultMapsComponent(
    private val mapsStoreFactory: MapsStoreFactory,
    private val onNavBackClick: () -> Unit,
    private val onMapClick: (GameMap) -> Unit,
    context: ComponentContext
) : MapsComponent, ComponentContext by context {

    private val store: MapsStore = instanceKeeper.getStore { mapsStoreFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is Maps.Label.MapNavigation -> onMapClick(it.map)
                    is Maps.Label.NavigationBack -> onNavBackClick()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<Maps.State> = store.stateFlow

    override fun onNavigationBackClick() {
        store.accept(Maps.Intent.OnNavigationBackClicked)
    }

    override fun onMapModeSelected(mode: MapMode?) {
        store.accept(Maps.Intent.OnMapModeSelected(mode))
    }

    override fun onGameMapClicked(map: GameMap) {
        store.accept(Maps.Intent.OnGameMapClicked(map))
    }

    override fun onCloseError() {
        store.accept(Maps.Intent.OnCloseError)
    }


}