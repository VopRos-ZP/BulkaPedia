package com.bulkapedia.compose.screens.dashboard.screens.categorymanage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.data.CallBack
import com.bulkapedia.data.Repository
import com.bulkapedia.data.categories.Category
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryManageViewModel @Inject constructor(
    private val categoriesRepository: Repository<Category>
) : ViewModel() {

    private val _categoriesFlow: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val categoriesFlow: StateFlow<List<Category>> = _categoriesFlow

    private var listener: ListenerRegistration? = null

    /** Doesn't create a new categories **/
    fun updateCategory(category: Category) {
        viewModelScope.launch { categoriesRepository.update(category) }
    }

    fun listenCategories() {
        listener = categoriesRepository.fetchAll(CallBack({
            viewModelScope.launch { _categoriesFlow.emit(it) }
        }) {})
    }

    fun removeCategory() {
        listener?.remove()
    }

}