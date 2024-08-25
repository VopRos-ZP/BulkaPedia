package ru.bulkapedia.presentation.screens.categories

import kotlinx.coroutines.flow.StateFlow
import ru.bulkapedia.domain.model.Category

interface CategoriesComponent {
    val model: StateFlow<Categories.State>

    fun onCategoryClick(category: Category)
}