package com.bulkapedia.data.comments

import com.bulkapedia.data.asMap
import com.bulkapedia.data.docToObject
import com.google.firebase.firestore.DocumentSnapshot

data class Comment(
    val commentId: String,
    val set: String,
    val from: String,
    val text: String,
    val date: String
) {

    companion object {

        fun DocumentSnapshot.toComment(): Comment? {
            return docToObject(CommentDTO::class.java) { dto ->
                Comment(id, dto.set, dto.from, dto.text, dto.date)
            }
        }

        fun Comment.toData(): Map<String, Any> = asMap().mapValues { it }

    }

}