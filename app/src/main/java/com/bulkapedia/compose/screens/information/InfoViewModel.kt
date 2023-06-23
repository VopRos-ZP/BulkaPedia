package com.bulkapedia.compose.screens.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.categories.Category
import com.bulkapedia.compose.data.repos.firestore.Repository
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val categoryRepository: Repository<Category>
) : ViewModel() {

    private val _categoryFlow: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val categoryFlow: StateFlow<List<Category>> = _categoryFlow

    private var categoryListener: ListenerRegistration? = null

    fun fetchCategories() {
        categoryListener = categoryRepository.fetchAll {
            viewModelScope.launch { _categoryFlow.emit(it) }
        }
    }

    fun dispose() = categoryListener?.remove()

}

