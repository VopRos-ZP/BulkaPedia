package ru.bulkapedia.presentation.ui.screens.gear_prices

sealed interface GearPricesSideEffect {
    data object OnBackClick : GearPricesSideEffect
}