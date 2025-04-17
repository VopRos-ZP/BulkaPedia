package ru.bulkapedia.presentation.ui.screens.gear_prices.component

import kotlinx.coroutines.flow.StateFlow
import ru.bulkapedia.presentation.ui.screens.gear_prices.mvi.GearPrices

interface GearPricesComponent {

    val state: StateFlow<GearPrices.State>

    fun onBackClick()

}