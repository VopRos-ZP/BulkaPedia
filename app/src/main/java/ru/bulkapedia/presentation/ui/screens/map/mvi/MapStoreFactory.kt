package ru.bulkapedia.presentation.ui.screens.map.mvi

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.bulkapedia.di.qualifiers.DefaultSF
import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.repository.MapsRepository
import ru.bulkapedia.presentation.ui.screens.map.mvi.MapStore.Intent
import ru.bulkapedia.presentation.ui.screens.map.mvi.MapStore.Label
import ru.bulkapedia.presentation.ui.screens.map.mvi.MapStore.State
import javax.inject.Inject

class MapStoreFactory @Inject constructor(
    @DefaultSF
    private val storeFactory: StoreFactory,
    private val mapsRepository: MapsRepository
) {

    fun create(gameMap: GameMap): MapStore = object : MapStore,
        Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
            name = "MapStore",
            initialState = State(map = gameMap),
            bootstrapper = coroutineBootstrapper {
                val handler = CoroutineExceptionHandler { _, e ->
                    Log.e("MapStore", e.stackTraceToString())

                    dispatch(Action.ErrorChanged(e.localizedMessage))
                }
                launch(handler) {
                    mapsRepository.map(gameMap.id).collect {
                        dispatch(Action.GameMapChanged(it))
                    }
                }
            },
            executorFactory = coroutineExecutorFactory {  },
            reducer = {
                when (it) {
                    is Msg.GameMapChanged -> copy(map = it.value)
                    is Msg.ToggleShowSpawnsChanged -> copy(isShowSpawns = it.value)
                    is Msg.ErrorChanged -> copy(error = it.value)
                }
            }
        ) {}

    sealed interface Msg {
        data class ToggleShowSpawnsChanged(val value: Boolean): Msg
        data class GameMapChanged(val value: GameMap): Msg
        data class ErrorChanged(val value: String?): Msg
    }

    sealed interface Action {
        data class GameMapChanged(val value: GameMap): Action
        data class ErrorChanged(val value: String?): Action
    }

}