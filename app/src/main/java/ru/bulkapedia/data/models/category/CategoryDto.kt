package ru.bulkapedia.data.models.category

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class CategoryDto(
    @DocumentId
    var id: String = "",
    var title: String = "",
    @PropertyName("is_enabled")
    var enabled: Boolean = false,
    @PropertyName("is_visible")
    var visible: Boolean = false
)
