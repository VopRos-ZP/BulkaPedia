package ru.bulkapedia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameMap(
    val id: String,
    val original: String,
    val spawns: String,
    val mode: MapMode
) : Parcelable
