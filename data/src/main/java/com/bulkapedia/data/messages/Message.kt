package com.bulkapedia.data.messages

import com.bulkapedia.data.asMap
import com.bulkapedia.data.docToObject
import com.google.firebase.firestore.DocumentSnapshot

data class Message(
    val messageId: String,
    val author: String,
    val date: String,
    val image: String,
    val receiver: String,
    val text: String,
    val read: Boolean
) {

    companion object {

        fun DocumentSnapshot.toMessage(): Message? {
            return docToObject(MessageDTO::class.java) { dto ->
                Message(
                    id, dto.author, dto.date,
                    dto.image, dto.receiver,
                    dto.text, dto.read
                )
            }
        }

        fun Message.toData(): Map<String, Any> = asMap().mapValues { it }

    }

}
