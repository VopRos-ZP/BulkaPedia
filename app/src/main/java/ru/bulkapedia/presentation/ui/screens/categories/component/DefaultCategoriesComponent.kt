package ru.bulkapedia.presentation.ui.screens.categories.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.presentation.extensions.componentScope
import ru.bulkapedia.presentation.ui.screens.categories.mvi.Categories
import ru.bulkapedia.presentation.ui.screens.categories.mvi.CategoriesStoreFactory
import javax.inject.Inject

class DefaultCategoriesComponent @AssistedInject constructor(
    private val categoriesStoreFactory: CategoriesStoreFactory,
    @Assisted("onCategoryClick") private val onCategoryClick: (Category) -> Unit,
    @Assisted("context") context: ComponentContext,
) : CategoriesComponent, ComponentContext by context {

    private val store = instanceKeeper.getStore { categoriesStoreFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is Categories.Label.NavigationCategory -> onCategoryClick(it.category)
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

    @AssistedFactory
    interface Factory {

        fun create(
            @Assisted("onCategoryClick") onCategoryClick: (Category) -> Unit,
            @Assisted("context") context: ComponentContext,
        ): DefaultCategoriesComponent

    }

}