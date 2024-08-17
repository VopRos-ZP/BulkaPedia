package ru.bulkapedia.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "gears")
data class GearDto(
    @PrimaryKey
    val id: String,
    val image: String,
    val cell: String,
    val set: String,
)
