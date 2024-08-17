package ru.bulkapedia.data.room.heroes

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "heroes")
data class HeroDto(
    @PrimaryKey
    val id: String,
    @SerialName("is_active")
    val isActive: Boolean,
    val fraction: String,
    val type: String,
    val imageUrl: String
)
