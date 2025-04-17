package ru.bulkapedia.data.models.comment

import java.util.Date


data class CommentDto(
    val id: String,
    val text: String,
    val author: String,
    val set: String,
    val date: Date
)
