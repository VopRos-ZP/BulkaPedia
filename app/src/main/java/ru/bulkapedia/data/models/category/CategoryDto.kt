package ru.bulkapedia.data.models.category

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class CategoryDto(
    @DocumentId
    val id: String = "",
    val title: String = "",
    @JvmField
    @PropertyName("is_enabled")
    val enabled: Boolean = false,
    @JvmField
    @PropertyName("is_visible")
    val visible: Boolean = false
)
