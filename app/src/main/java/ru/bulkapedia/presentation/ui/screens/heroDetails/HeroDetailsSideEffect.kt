package ru.bulkapedia.presentation.ui.screens.heroDetails

sealed interface HeroDetailsSideEffect {
    data object OnBackClick : HeroDetailsSideEffect
}