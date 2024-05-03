package ru.bulkapedia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gear(
    val id: String,
    val cell: GearCell,
    val set: GearSet,
    val effects: List<Effect>
) : Parcelable
