package com.vopros.data.comment

import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.domain.comment.Comment

fun DocumentSnapshot.toComment(): Comment? {
    return try {
        Comment(
            id, getString("set")!!,
            getString("from")!!,
            getString("text")!!,
            getString("date")!!
        )
    } catch (_: Exception) { null }
}

fun Comment.toData(): Map<String, Any> {
    return mapOf(
        "set" to set,
        "from" to from,
        "text" to text,
        "date" to date
    )
}