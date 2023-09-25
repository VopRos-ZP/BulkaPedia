package com.vopros.bulkapedia.ui.screens.categories

import com.vopros.bulkapedia.category.CategoryRepository
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
import com.vopros.bulkapedia.ui.view.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): IntentViewModel<CategoriesViewIntent>() {

    override var reducer: Reducer<CategoriesViewIntent> = Reducer { _, _ ->
        fetchCategories()
    }

    private suspend fun fetchCategories() {
        innerState.emit(ViewState.Success(categoryRepository.fetchAll()))
    }

}