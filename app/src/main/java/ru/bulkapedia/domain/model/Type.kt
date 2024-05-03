package ru.bulkapedia.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Type(
    val id: Long,
    val title: String
)
