package ru.bulkapedia.presentation.screens.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.domain.usecase.category.GetAllCategoryUseCase

class CategoriesViewModel(
    private val getAllCategoryUseCase: GetAllCategoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Category>())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAllCategoryUseCase().collect {
                _state.emit(it.filter(Category::isVisible))
            }
        }
    }

}