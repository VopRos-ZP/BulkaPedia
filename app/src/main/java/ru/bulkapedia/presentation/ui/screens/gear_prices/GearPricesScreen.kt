package ru.bulkapedia.presentation.ui.screens.gear_prices

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.bulkapedia.presentation.ui.screens.gear_prices.component.GearPricesComponent

@Composable
fun GearPricesScreen(
    component: GearPricesComponent
) {
    val state by component.state.collectAsState()


}