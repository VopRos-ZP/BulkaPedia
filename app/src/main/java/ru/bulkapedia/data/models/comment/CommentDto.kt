package ru.bulkapedia.data.models.comment

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference

data class CommentDto(
    @DocumentId
    val id: String,
    val text: String,
    val author: DocumentReference,
    val set: String,
    val date: Timestamp
)
