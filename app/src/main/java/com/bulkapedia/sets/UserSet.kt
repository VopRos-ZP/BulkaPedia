package com.bulkapedia.sets

import java.io.Serializable

data class UserSet(
    val setId: String,
    val from: String,
    val hero: Int,
    val gears: Map<GearCell, Int>,
    var likes: Int,
    var userLikeIds: MutableList<String>
) : Serializable
