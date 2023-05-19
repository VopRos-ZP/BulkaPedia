package com.vopros.domain.message

import com.vopros.domain.Entity
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    var messageId: String = "",
    var author: String = "",
    var date: String = "",
    var image: String = "",
    var receiver: String = "",
    var text: String = "",
    var read: Boolean = false
): Entity() {
    override var id: String
        get() = messageId
        set(value) { messageId = value }

    override fun toData(): Map<String, Any> {
        return mapOf(
            "author" to author,
            "date" to date,
            "image" to image,
            "receiver" to receiver,
            "text" to text,
            "read" to read
        )
    }
}
