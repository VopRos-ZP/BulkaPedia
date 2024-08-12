package ru.bulkapedia.presentation.screens.categories

import ru.bulkapedia.domain.category.Category

data class CategoriesViewState(
    val categories: List<Category> = emptyList()
)
