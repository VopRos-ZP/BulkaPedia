package ru.bulkapedia.data.models.category


data class CategoryDto(
    val id: String = "",
    val title: String = "",
    @JvmField
    val enabled: Boolean = false,
    @JvmField
    val visible: Boolean = false
)
