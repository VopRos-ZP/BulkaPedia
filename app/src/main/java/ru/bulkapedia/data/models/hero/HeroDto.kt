package ru.bulkapedia.data.models.hero

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeroDto(
    @SerialName("id")
    val id: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("type")
    val type: String
)
