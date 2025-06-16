package ru.bulkapedia.presentation.ui.screens.heroDetails

import ru.bulkapedia.domain.model.HeroType

data class HeroDetailsViewState(
    val isLoading: Boolean = false,
    val id: String,
    val imageUrl: String = "",
    val type: HeroType = HeroType.SHORTGUN,
)
