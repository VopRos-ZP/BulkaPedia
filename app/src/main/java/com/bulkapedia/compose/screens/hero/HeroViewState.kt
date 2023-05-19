package com.bulkapedia.compose.screens.hero

import com.vopros.domain.hero.Hero
import com.vopros.domain.set.Set

sealed class HeroViewState {
    object Loading: HeroViewState()
    data class Active(val hero: Hero, val sets: List<Set>): HeroViewState()
    data class Error(val msg: String): HeroViewState()
}