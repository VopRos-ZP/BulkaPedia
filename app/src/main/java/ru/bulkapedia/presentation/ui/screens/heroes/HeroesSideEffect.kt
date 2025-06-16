package ru.bulkapedia.presentation.ui.screens.heroes

sealed interface HeroesSideEffect {
    data object OnBackClick : HeroesSideEffect
    data class OnHeroClick(val value: String) : HeroesSideEffect
}