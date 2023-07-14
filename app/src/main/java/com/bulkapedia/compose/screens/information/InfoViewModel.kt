package com.bulkapedia.compose.screens.information

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bulkapedia.Callback
import bulkapedia.StoreRepository
import bulkapedia.categories.Category
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor(
    private val categoryRepository: StoreRepository<Category>
) : ViewModel() {

    private val _categoryFlow: MutableStateFlow<List<Category>> = MutableStateFlow(emptyList())
    val categoryFlow: StateFlow<List<Category>> = _categoryFlow

    private var categoryListener: ListenerRegistration? = null

    fun fetchCategories() {
        categoryListener = categoryRepository.listenAll(Callback({
            viewModelScope.launch { _categoryFlow.emit(it) }
        }))
    }

    fun dispose() = categoryListener?.remove()

}

