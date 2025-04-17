package ru.bulkapedia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val nickname: String,
    val isAdmin: Boolean = false
) : Parcelable
