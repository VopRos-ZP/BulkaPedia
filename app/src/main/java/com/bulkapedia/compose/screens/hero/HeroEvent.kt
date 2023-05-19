package com.bulkapedia.compose.screens.hero

sealed class HeroEvent {
    data class StartScreen(val heroId: String): HeroEvent()
}