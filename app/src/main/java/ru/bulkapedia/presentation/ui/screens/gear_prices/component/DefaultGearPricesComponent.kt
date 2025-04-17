package ru.bulkapedia.presentation.ui.screens.gear_prices.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.bulkapedia.presentation.ui.screens.gear_prices.mvi.GearPricesStoreFactory

class DefaultGearPricesComponent(
    private val storeFactory: GearPricesStoreFactory,
    private val onBack: () -> Unit,
    context: ComponentContext
): GearPricesComponent, ComponentContext by context {

    private val store = instanceKeeper.getStore { storeFactory.create() }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state = store.stateFlow

    override fun onBackClick() {
        onBack()
    }

}