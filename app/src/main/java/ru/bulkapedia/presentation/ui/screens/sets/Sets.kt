package ru.bulkapedia.presentation.ui.screens.sets

import ru.bulkapedia.domain.model.Hero
import ru.bulkapedia.domain.model.HeroType

object Sets {

    data class State(
        val heroes: List<Hero> = emptyList(),
        val selectedHeroType: HeroType? = null,
        val error: String? = null
    )

}