package com.vopros.data.category

import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.domain.categories.Category

fun DocumentSnapshot.toCategory(): Category? {
    return try {
        Category(
            id, getString("destination")!!,
            getBoolean("enabled") ?: false,
            getString("icon")!!,
            getString("title")!!,
            getString("subTitle")!!
        )
    } catch (_: Exception) { null }
}