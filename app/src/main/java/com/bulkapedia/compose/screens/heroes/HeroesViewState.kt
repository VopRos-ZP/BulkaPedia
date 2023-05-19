package com.bulkapedia.compose.screens.heroes

import com.vopros.domain.hero.Hero

sealed class HeroesViewState {
    object Loading: HeroesViewState()
    data class Active(val heroes: List<Hero>): HeroesViewState()
    data class Error(val msg: String): HeroesViewState()
}