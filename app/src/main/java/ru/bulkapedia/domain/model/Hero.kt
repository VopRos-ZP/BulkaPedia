package ru.bulkapedia.domain.model

data class Hero(
    val id: String,
    val type: HeroType,
    val active: Boolean,
    val image: String,
)
