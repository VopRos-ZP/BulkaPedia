package ru.bulkapedia.data.repository.hero

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hero(
    val id: String,
    @SerialName("is_active")
    val isActive: Boolean,
    val fraction: String,
    val difficult: List<Float>,
    val type: String,
    val imageUrl: String,
)
