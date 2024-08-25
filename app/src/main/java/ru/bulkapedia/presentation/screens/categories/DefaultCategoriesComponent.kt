package ru.bulkapedia.presentation.screens.categories

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import ru.bulkapedia.domain.model.Category

class DefaultCategoriesComponent(
    private val categoriesStoreFactory: CategoriesStoreFactory,
    private val onCategoryClick: (String) -> Unit,
    componentContext: ComponentContext,
) : CategoriesComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { categoriesStoreFactory.create() }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<Categories.State> = store.stateFlow

    override fun onCategoryClick(category: Category) {
        onCategoryClick(category.route)
    }

}