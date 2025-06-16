package ru.bulkapedia.presentation.ui.screens.heroDetails

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.bulkapedia.domain.repository.HeroesRepository

class HeroDetailsViewModel(
    id: String,
    private val heroesRepository: HeroesRepository
) : ContainerHost<HeroDetailsViewState, HeroDetailsSideEffect>, ViewModel() {

    override val container = container<HeroDetailsViewState, HeroDetailsSideEffect>(
        initialState = HeroDetailsViewState(id = id),
        onCreate = {
            reduce { state.copy(isLoading = true) }
            repeatOnSubscription {
                heroesRepository.listenHero(id).collect {
                    reduce {
                        state.copy(
                            isLoading = false,
                            imageUrl = it.imageUrl,
                            type = it.type
                        )
                    }
                }
            }
        }
    )

    fun onBackClick() = intent {
        postSideEffect(HeroDetailsSideEffect.OnBackClick)
    }

}