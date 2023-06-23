package com.bulkapedia.compose.data.repos.comments

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
        return Json.decodeFromString(Json.encodeToString(this))
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
