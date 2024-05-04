package ru.bulkapedia.presentation.ui.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.domain.repository.CategoryRepository
import ru.bulkapedia.presentation.ui.screens.categories.mvi.Categories
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository
): ViewModel() {

    private val _state = MutableStateFlow(Categories.State())
    val state = _state.asStateFlow()

    fun fetchCategories() {
        viewModelScope.launch {
            categoryRepository.categories.collect {
                _state.value = state.value.copy(categories = it.filter(Category::visible))
            }
        }
    }

    fun closeError() {
        _state.value = state.value.copy(error = null)
    }

}