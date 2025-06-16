package ru.bulkapedia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val id: Int,
    val name: String,
    val route: String,
    val isEnable: Boolean,
    val isVisible: Boolean
) : Parcelable