package com.vopros.data.message

import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.domain.message.Message

fun DocumentSnapshot.toMessage(): Message? {
    return try {
        Message(
            id, getString("author")!!,
            getString("date")!!,
            getString("image")!!,
            getString("receiver")!!,
            getString("text")!!,
            getBoolean("read") ?: false
        )
    } catch (_: Exception) { null }
}