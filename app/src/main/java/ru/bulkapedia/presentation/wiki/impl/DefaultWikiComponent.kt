package ru.bulkapedia.presentation.wiki.impl

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.presentation.wiki.WikiComponent

class DefaultWikiComponent(
    context: ComponentContext,
    private val onClick: (Category) -> Unit
) : WikiComponent, ComponentContext by context {

    private val _model = MutableStateFlow(
        WikiComponent.Model(
            isLoading = true,
            categories = emptyList()
        )
    )

    override val model: StateFlow<WikiComponent.Model>
        get() = _model.asStateFlow()

    override fun setLoading(isLoading: Boolean) {
        _model.value = model.value.copy(isLoading = isLoading)
    }

    override fun onCategoryClick(category: Category) {
        onClick(category)
    }

}