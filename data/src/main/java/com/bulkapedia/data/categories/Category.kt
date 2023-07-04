package com.bulkapedia.data.categories

import com.bulkapedia.data.asMap
import com.bulkapedia.data.docToObject
import com.google.firebase.firestore.DocumentSnapshot

data class Category(
    val categoryId: String,
    val title: String,
    val subTitle: String,
    val enabled: Boolean,
    val destination: String,
    val icon: String
) {

    companion object {

        fun DocumentSnapshot.toCategory(): Category? {
            return docToObject(CategoryDTO::class.java) { dto ->
                Category(id, dto.title,
                    dto.subTitle, dto.enabled,
                    dto.destination, dto.icon
                )
            }
        }

        fun Category.toData(): Map<String, Any> = asMap().mapValues { it }

    }

}