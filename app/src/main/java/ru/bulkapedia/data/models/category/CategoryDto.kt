package ru.bulkapedia.data.models.category

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String,
    @SerialName("route")
    val route: String,
    @SerialName("is_enable")
    val isEnable: Boolean,
    @SerialName("is_visible")
    val isVisible: Boolean,
)
