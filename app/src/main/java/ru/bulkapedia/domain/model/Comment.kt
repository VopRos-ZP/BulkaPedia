package ru.bulkapedia.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class Comment(
    val id: String,
    val author: User,
    val text: String,
    val date: LocalDateTime,
    val set: String
) : Parcelable
