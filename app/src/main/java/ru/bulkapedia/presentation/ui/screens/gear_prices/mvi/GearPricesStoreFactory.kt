package ru.bulkapedia.presentation.ui.screens.gear_prices.mvi

import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.repository.GearPriceRepository
import ru.bulkapedia.presentation.ui.screens.gear_prices.mvi.GearPrices.State
import ru.bulkapedia.presentation.ui.screens.gear_prices.mvi.GearPrices.Intent
import ru.bulkapedia.presentation.ui.screens.gear_prices.mvi.GearPrices.Action
import ru.bulkapedia.presentation.ui.screens.gear_prices.mvi.GearPrices.Msg
import ru.bulkapedia.presentation.ui.screens.gear_prices.mvi.GearPrices.Label

class GearPricesStoreFactory(
    private val storeFactory: StoreFactory,
    private val gearPriceRepository: GearPriceRepository
) {

    fun create(): GearPricesStore = object : GearPricesStore, Store<Intent, State, Label> by storeFactory.create<Intent, Action, Msg, State, Label>(
        name = "GearPricesStore",
        initialState = State(),
        bootstrapper = coroutineBootstrapper {
            launch {
                dispatch(Action.OnGearPricesChange(gearPriceRepository.fetchAll()))
            }
        },
        executorFactory = coroutineExecutorFactory {
            onAction<Action.OnGearPricesChange> {
                dispatch(Msg.OnGearPricesChange(it.value))
            }
        },
        reducer = {
            when (it) {
                is Msg.OnGearPricesChange -> copy(gearPrices = it.value)
            }
        }
    ) {}

}