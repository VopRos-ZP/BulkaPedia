package ru.bulkapedia.presentation.ui.screens.splash

sealed interface SplashSideEffect {
    data object OnFinish : SplashSideEffect
}