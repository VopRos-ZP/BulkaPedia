package com.bulkapedia.views.temps.models

import java.io.Serializable

data class TagModel (
    val color: Int,
    val text: Int,
    var selected: Boolean
) : Serializable