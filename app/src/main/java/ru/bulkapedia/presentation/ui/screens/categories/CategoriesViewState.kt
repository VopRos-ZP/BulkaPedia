package ru.bulkapedia.presentation.ui.screens.categories

import ru.bulkapedia.domain.model.Category

data class CategoriesViewState(
    val categories: List<Category> = emptyList()
)
