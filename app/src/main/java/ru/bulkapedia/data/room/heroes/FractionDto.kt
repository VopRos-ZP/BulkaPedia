package ru.bulkapedia.data.room.heroes

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "fractions")
data class FractionDto(
    @PrimaryKey
    val id: String,
    val imageUrl: String,
)
