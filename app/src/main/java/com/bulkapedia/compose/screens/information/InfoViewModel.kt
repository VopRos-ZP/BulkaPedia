package com.bulkapedia.compose.screens.information

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.category.Category
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoViewModel @Inject constructor() : ViewModel() {

    val categoryLiveData: MutableLiveData<List<Category>> = MutableLiveData(emptyList())

    private var categoryListener: ListenerRegistration? = null

    fun listenCategories() {
        val db = Database()
        db.addCategoriesSnapshotListener { categories -> categoryLiveData.postValue(categories) }
    }

    fun removeListeners() {
        categoryListener?.remove()
    }

}

