package ru.bulkapedia.domain.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val title: String,
    @SerialName("is_enabled")
    val isEnabled: Boolean,
    @SerialName("is_visible")
    val isVisible: Boolean,
)
