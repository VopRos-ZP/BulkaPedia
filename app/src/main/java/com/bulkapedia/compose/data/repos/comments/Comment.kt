package com.bulkapedia.compose.data.repos.comments

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Comment(
    @Transient
    private var commentId: String = "",
    val set: String,
    val from: String,
    val text: String,
    val date: String
): Entity() {

    override var id: String
        get() = commentId
        set(value) { commentId = value }

    override fun toData(): MutableMap<String, Any> {
        return mutableMapOf(
            "set" to set,
            "from" to from,
            "text" to text,
            "date" to date
        )
    }

    companion object {
        fun DocumentSnapshot.toComment(): Comment? {
            return try {
                Comment(id,
                    get("set") as String,
                    get("from") as String,
                    get("text") as String,
                    get("date") as String
                )
            } catch (_: Exception) { null }
        }
    }

}
