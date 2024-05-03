package ru.bulkapedia.presentation.wiki

import kotlinx.coroutines.flow.StateFlow
import ru.bulkapedia.domain.model.Category

interface WikiComponent {

    val model: StateFlow<Model>

    fun setLoading(isLoading: Boolean = false)

    fun onCategoryClick(category: Category)

    data class Model(
        val isLoading: Boolean,
        val categories: List<Category>,
    )

}