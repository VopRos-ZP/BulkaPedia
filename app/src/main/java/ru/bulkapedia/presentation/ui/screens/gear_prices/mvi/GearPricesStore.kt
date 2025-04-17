package ru.bulkapedia.presentation.ui.screens.gear_prices.mvi

import com.arkivanov.mvikotlin.core.store.Store

interface GearPricesStore : Store<GearPrices.Intent, GearPrices.State, GearPrices.Label>