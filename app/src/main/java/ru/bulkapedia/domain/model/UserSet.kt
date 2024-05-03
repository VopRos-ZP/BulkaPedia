package ru.bulkapedia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserSet(
    val id: String,
    val gears: List<Gear>,
    val author: User,
    val likes: List<String>
) : Parcelable
