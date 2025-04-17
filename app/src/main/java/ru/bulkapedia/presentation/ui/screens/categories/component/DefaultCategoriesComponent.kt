package ru.bulkapedia.presentation.ui.screens.categories.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.presentation.extensions.componentScope
import ru.bulkapedia.presentation.ui.screens.categories.mvi.Categories
import ru.bulkapedia.presentation.ui.screens.categories.mvi.CategoriesStoreFactory

class DefaultCategoriesComponent(
    private val onCategory: (Category) -> Unit,
    private val categoriesStoreFactory: CategoriesStoreFactory,
    context: ComponentContext,
) : CategoriesComponent, ComponentContext by context {

    private val store = instanceKeeper.getStore { categoriesStoreFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is Categories.Label.NavigationCategory -> onCategory(it.category)
                    is Categories.Label.Snackbar -> TODO()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<Categories.State> = store.stateFlow

    override fun onCategoryClick(category: Category) {
        store.accept(Categories.Intent.OnCategoryClick(category))
    }

    override fun onCloseError() {
        store.accept(Categories.Intent.CloseError)
    }

}