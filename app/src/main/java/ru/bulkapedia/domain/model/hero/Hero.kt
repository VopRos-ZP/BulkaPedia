package ru.bulkapedia.domain.model.hero

data class Hero(
    val id: String,
    val isActive: Boolean,
    val fraction: String,
    val fractionImageUrl: String,
    val type: HeroType,
    val imageUrl: String,
)
