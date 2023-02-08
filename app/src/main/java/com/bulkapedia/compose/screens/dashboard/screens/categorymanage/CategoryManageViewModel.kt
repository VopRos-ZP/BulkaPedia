package com.bulkapedia.compose.screens.dashboard.screens.categorymanage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.category.Category
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryManageViewModel @Inject constructor() : ViewModel() {

    val liveData: MutableLiveData<List<Category>> = MutableLiveData(emptyList())

    private var listener: ListenerRegistration? = null

    /** Doesn't create a new categories **/
    fun updateCategory(category: Category) {
        Database().updateCategory(category)
    }

    fun listenCategories() {
        listener = Database().addCategoriesSnapshotListener(liveData::postValue)
    }

    fun removeCategory() {
        listener?.remove()
    }

}