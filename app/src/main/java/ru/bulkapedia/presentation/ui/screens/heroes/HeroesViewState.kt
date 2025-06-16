package ru.bulkapedia.presentation.ui.screens.heroes

import ru.bulkapedia.domain.model.Hero

data class HeroesViewState(
    val isLoading: Boolean = false,
    val heroes: List<Hero> = emptyList()
)
