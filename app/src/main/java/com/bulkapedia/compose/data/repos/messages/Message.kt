package com.bulkapedia.compose.data.repos.messages

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Message(
    @Transient
    private var messageId: String = "",
    val author: String,
    val date: String,
    val image: String,
    val receiver: String,
    val text: String,
    val read: Boolean
): Entity() {

    override var id: String
        get() = messageId
        set(value) { messageId = value }

    override fun toData(): MutableMap<String, Any> {
        return Json.decodeFromString(Json.encodeToString(this))
    }

    companion object {
        fun DocumentSnapshot.toMessage(): Message? {
            return try {
                toObject(Message::class.java).apply { this?.id = id }
            } catch (_: Exception) { null }
        }
    }

}
