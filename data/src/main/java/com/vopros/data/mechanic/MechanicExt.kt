package com.vopros.data.mechanic

import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.domain.mechanics.Mechanic

fun DocumentSnapshot.toMechanic(): Mechanic? {
    return try {
        Mechanic(
            id, getString("title")!!,
            getString("video")!!,
            getString("icon")!!,
            getString("description")!!
        )
    } catch (_: Exception) { null }
}
