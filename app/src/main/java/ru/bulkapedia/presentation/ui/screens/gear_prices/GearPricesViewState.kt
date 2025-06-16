package ru.bulkapedia.presentation.ui.screens.gear_prices

import ru.bulkapedia.domain.model.GearPrice

data class GearPricesViewState(
    val isLoading: Boolean = false,
    val gearPrices: List<GearPrice> = emptyList(),
)
