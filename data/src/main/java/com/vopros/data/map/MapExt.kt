package com.vopros.data.map

import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.domain.map.Map

fun DocumentSnapshot.toMap(): Map? {
    return try {
        Map(
            id, getString("image")!!,
            getString("imageSpawns")!!,
            getString("name")!!,
            getString("mode")!!
        )
    } catch (_: Exception) { null }
}
