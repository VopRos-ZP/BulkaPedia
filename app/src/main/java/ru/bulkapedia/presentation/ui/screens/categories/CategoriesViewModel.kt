package ru.bulkapedia.presentation.ui.screens.categories

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.domain.repository.CategoryRepository

class CategoriesViewModel(
    private val categoryRepository: CategoryRepository
): ContainerHost<CategoriesViewState, CategoriesSideEffect>, ViewModel() {

    override val container = container<CategoriesViewState, CategoriesSideEffect>(
        initialState = CategoriesViewState(),
        onCreate = {
            repeatOnSubscription {
                categoryRepository.categories.collect {
                    reduce { state.copy(categories = it) }
                }
            }
        }
    )

    fun onCategoryClick(category: Category) = intent {
        postSideEffect(CategoriesSideEffect.OnCategoryClick(category.route))
    }

}
