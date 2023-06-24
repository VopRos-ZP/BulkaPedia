package com.bulkapedia.compose.data.repos.messages

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

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
        return mutableMapOf(
            "author" to author,
            "date" to date,
            "image" to image,
            "receiver" to receiver,
            "text" to text,
            "read" to read
        )
    }

    companion object {
        fun DocumentSnapshot.toMessage(): Message? {
            return try {
                Message(id,
                    get("author") as String,
                    get("date") as String,
                    get("image") as String,
                    get("receiver") as String,
                    get("text") as String,
                    get("read") as Boolean
                )
            } catch (_: Exception) { null }
        }
    }

}
