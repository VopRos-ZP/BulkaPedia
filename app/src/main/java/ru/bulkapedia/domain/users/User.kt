package ru.bulkapedia.domain.users

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val nickname: String,
    val email: String,
    val password: String,
    val created: String,
    val updated: String,
    val role: String,
)
