package com.vopros.bulkapedia.category

import com.google.firebase.firestore.DocumentId

data class CategoryDTO(
    @DocumentId var id: String = "",
    var title: String = "",
    var subTitle: String = "",
    var enabled: Boolean = false,
    var icon: String = "",
    var destination: String = "",
)
