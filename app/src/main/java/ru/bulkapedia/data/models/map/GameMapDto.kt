package ru.bulkapedia.data.models.map

import com.google.firebase.firestore.DocumentId

data class GameMapDto(
    @DocumentId
    val id: String,
    val original: String,
    val spawns: String,
    val mode: String
)
