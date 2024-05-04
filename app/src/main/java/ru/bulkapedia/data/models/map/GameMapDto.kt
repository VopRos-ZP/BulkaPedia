package ru.bulkapedia.data.models.map

import com.google.firebase.firestore.DocumentId
import ru.bulkapedia.domain.model.MapMode

data class GameMapDto(
    @DocumentId
    val id: String = "",
    val original: String = "",
    val spawns: String = "",
    val mode: String = MapMode.BATTLE_ROYALE.name
)
