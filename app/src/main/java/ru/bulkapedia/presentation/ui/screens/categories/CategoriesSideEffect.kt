package ru.bulkapedia.presentation.ui.screens.categories

sealed interface CategoriesSideEffect {
    data class OnCategoryClick(val value: String) : CategoriesSideEffect
}