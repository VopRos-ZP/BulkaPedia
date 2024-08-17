package ru.bulkapedia.data.room.user

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "users")
data class UserDto(
    @PrimaryKey
    val id: String,
    val nickname: String,
    val email: String,
    val password: String,
    val role: String,
)
