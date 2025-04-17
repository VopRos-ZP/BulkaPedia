package ru.bulkapedia.presentation.ui.screens.gear_prices.mvi

import com.arkivanov.decompose.ComponentContext
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import ru.bulkapedia.domain.model.GearPrice

object GearPrices {

    data class State(
        val gearPrices: List<GearPrice> = emptyList()
    )

    sealed interface Intent

    sealed interface Label

    sealed interface Action {
        data class OnGearPricesChange(val value: List<GearPrice>) : Action
    }

    sealed interface Msg {
        data class OnGearPricesChange(val value: List<GearPrice>) : Msg
    }

    fun params(
        onBackClick: () -> Unit,
        context: ComponentContext
    ): ParametersDefinition = {
        parametersOf(
            onBackClick,
            context
        )
    }

}