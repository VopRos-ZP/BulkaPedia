package ru.bulkapedia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class GameMap(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val spawnsUrl: String,
    val mode: MapMode
) : Parcelable
