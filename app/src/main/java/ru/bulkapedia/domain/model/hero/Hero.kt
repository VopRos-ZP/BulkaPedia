package ru.bulkapedia.domain.model.hero

data class Hero(
    val id: String,
    val isActive: Boolean,
    val fraction: String,
    val difficult: List<Float>,
    val type: String,
    val imageUrl: String,
)
