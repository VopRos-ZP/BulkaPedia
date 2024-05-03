package ru.bulkapedia.presentation.ui.screens.categories.component

import kotlinx.coroutines.flow.StateFlow
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.presentation.ui.screens.categories.mvi.Categories

interface CategoriesComponent {

    val state: StateFlow<Categories.State>

    fun onCategoryClick(category: Category)
    fun onCloseError()

}