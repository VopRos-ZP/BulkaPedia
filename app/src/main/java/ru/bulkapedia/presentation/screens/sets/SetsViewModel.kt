package ru.bulkapedia.presentation.screens.sets

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.usecase.GetFractionsUseCase
import ru.bulkapedia.domain.usecase.GetHeroesUseCase
import ru.bulkapedia.presentation.mvi.BaseViewModel

class SetsViewModel(
    private val getHeroesUseCase: GetHeroesUseCase,
    private val getFractionsUseCase: GetFractionsUseCase
) : BaseViewModel<SetsViewState, Any, SetsViewIntent>(
    initialState = SetsViewState()
) {

    override fun intent(intent: SetsViewIntent) {
        when (intent) {
            is SetsViewIntent.Launch -> launch()
        }
    }

    private fun launch() {
        viewModelScope.launch {
            launch {
                getHeroesUseCase().collect {
                    viewState = viewState.copy(heroes = it)
                }
            }
            launch {
                getFractionsUseCase().collect {
                    viewState = viewState.copy(fractions = it)
                }
            }
        }
    }

}