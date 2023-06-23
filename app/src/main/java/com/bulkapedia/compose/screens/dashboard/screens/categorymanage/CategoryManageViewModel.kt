package com.bulkapedia.compose.screens.dashboard.screens.categorymanage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.categories.CategoriesRepository
import com.bulkapedia.compose.data.repos.categories.Category
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryManageViewModel @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) : ViewModel() {

    private val _categoriesFlow: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val categoriesFlow: StateFlow<List<Category>> = _categoriesFlow

    private var listener: ListenerRegistration? = null

    /** Doesn't create a new categories **/
    fun updateCategory(category: Category) {
        categoriesRepository.update(category)
    }

    fun listenCategories() {
        listener = categoriesRepository.fetchAll {
            viewModelScope.launch { _categoriesFlow.emit(it) }
        }
    }

    fun removeCategory() {
        listener?.remove()
    }

}