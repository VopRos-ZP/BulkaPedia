package ru.bulkapedia.presentation.screens.sets

sealed interface SetsViewIntent {
    data object Launch : SetsViewIntent
}