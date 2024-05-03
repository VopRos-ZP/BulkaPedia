package ru.bulkapedia.data.models.map

import com.google.firebase.firestore.DocumentId

data class GameMapDto(
    @DocumentId
    val id: String,
    val spawns: String,
    val mode: String
)
