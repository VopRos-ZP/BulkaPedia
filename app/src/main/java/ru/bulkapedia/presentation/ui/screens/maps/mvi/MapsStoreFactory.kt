package ru.bulkapedia.presentation.ui.screens.maps.mvi

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.repository.MapsRepository

class MapsStoreFactory(
    private val storeFactory: StoreFactory,
    private val mapsRepository: MapsRepository
) {

    fun create(): MapsStore = object : MapsStore,
        Store<Maps.Intent, Maps.State, Maps.Label> by storeFactory.create<Maps.Intent, Maps.Action, Maps.Msg, Maps.State, Maps.Label>(
            name = "MapsStore",
            initialState = Maps.State(),
            bootstrapper = coroutineBootstrapper {
                val handler = CoroutineExceptionHandler { _, throwable ->
                    Log.e("MapsStore", throwable.stackTraceToString())

                    dispatch(Maps.Action.ErrorChanged(throwable.localizedMessage))
                }
                launch(handler) {

                }
            },
            executorFactory = coroutineExecutorFactory {
                onAction<Maps.Action.GameMapsChanged> { dispatch(Maps.Msg.MapsChanged(it.value)) }
                onAction<Maps.Action.ErrorChanged> { dispatch(Maps.Msg.ErrorChanged(it.value)) }

                onIntent<Maps.Intent.OnGameMapClicked> {
                    publish(Maps.Label.MapNavigation(it.map))
                }
                onIntent<Maps.Intent.OnMapModeSelected> {
                    dispatch(Maps.Msg.SelectedModeChanged(it.mode))
                }
                onIntent<Maps.Intent.OnCloseError> { dispatch(Maps.Msg.ErrorChanged(null)) }
                onIntent<Maps.Intent.OnNavigationBackClicked> { publish(Maps.Label.NavigationBack) }
            },
            reducer = {
                when (it) {
                    is Maps.Msg.ErrorChanged -> copy(error = it.value)
                    is Maps.Msg.LoadingChanged -> copy(isLoading = it.value)
                    is Maps.Msg.MapsChanged -> copy(maps = it.value)
                    is Maps.Msg.SelectedModeChanged -> copy(selectedMode = it.value)
                }
            }
        ) {}

}