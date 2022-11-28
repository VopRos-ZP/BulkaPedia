package com.bulkapedia.sets

import java.io.Serializable

data class UserSet(
    val setId: String,
    val from: String,
    val hero: String,
    val gears: Map<GearCell, String>,
    var likes: Int,
    var userLikeIds: MutableList<String>
) : Serializable
