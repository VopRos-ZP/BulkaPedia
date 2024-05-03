package ru.bulkapedia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: String,
    val title: String,
    val enabled: Boolean,
    val visible: Boolean
) : Parcelable