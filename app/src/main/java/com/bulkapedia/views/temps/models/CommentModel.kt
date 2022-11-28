package com.bulkapedia.views.temps.models

import java.io.Serializable

data class CommentModel (
    val author: String,
    val date: String,
    val text: String,
    val setId: String
) : Serializable