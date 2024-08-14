package ru.bulkapedia.data.repository.hero

import kotlinx.serialization.Serializable

@Serializable
data class Fraction(
    val id: String,
    val imageUrl: String,
)
