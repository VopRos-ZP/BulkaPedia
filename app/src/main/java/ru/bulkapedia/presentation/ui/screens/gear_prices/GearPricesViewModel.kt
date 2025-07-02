package ru.bulkapedia.presentation.ui.screens.gear_prices

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.bulkapedia.domain.repository.GearPriceRepository

class GearPricesViewModel(
    private val gearPriceRepository: GearPriceRepository
) : ContainerHost<GearPricesViewState, GearPricesSideEffect>, ViewModel() {

    override val container = container<GearPricesViewState, GearPricesSideEffect>(
        initialState = GearPricesViewState(),
        onCreate = {
            reduce { state.copy(isLoading = true) }
            delay(500)
            repeatOnSubscription {
                gearPriceRepository.gearPrices.collect {
                    reduce { state.copy(isLoading = false, gearPrices = it) }
                }
            }
        }
    )

    fun onBackClick() = intent {
        postSideEffect(GearPricesSideEffect.OnBackClick)
    }

}