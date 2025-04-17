package ru.bulkapedia.data.models.map

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameMapDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("spawns_url")
    val spawnsUrl: String,
    @SerialName("game_mode")
    val mode: String
)
