package ru.bulkapedia.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val nickname: String,
    val email: String,
    val password: String,
    val role: String,
)
