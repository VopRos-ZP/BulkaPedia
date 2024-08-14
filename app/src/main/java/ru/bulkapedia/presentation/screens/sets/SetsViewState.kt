package ru.bulkapedia.presentation.screens.sets

import ru.bulkapedia.domain.model.Fraction
import ru.bulkapedia.domain.model.hero.Hero

data class SetsViewState(
    val heroes: List<Hero> = emptyList(),
    val fractions: List<Fraction> = emptyList()
)
