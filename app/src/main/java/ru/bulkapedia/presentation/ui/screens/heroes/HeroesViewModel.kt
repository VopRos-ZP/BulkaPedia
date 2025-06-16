package ru.bulkapedia.presentation.ui.screens.heroes

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.bulkapedia.domain.model.Hero
import ru.bulkapedia.domain.repository.HeroesRepository

class HeroesViewModel(
    private val heroesRepository: HeroesRepository
) : ContainerHost<HeroesViewState, HeroesSideEffect>, ViewModel() {

    override val container = container<HeroesViewState, HeroesSideEffect>(
        initialState = HeroesViewState(),
        onCreate = {
            reduce { state.copy(isLoading = true) }
            repeatOnSubscription {
                heroesRepository.heroes.collect {
                    reduce { state.copy(heroes = it, isLoading = false) }
                }
            }
        }
    )

    fun onBackClick() = intent {
        postSideEffect(HeroesSideEffect.OnBackClick)
    }

    fun onHeroClick(hero: Hero) = intent {
        postSideEffect(HeroesSideEffect.OnHeroClick(hero.id))
    }

}