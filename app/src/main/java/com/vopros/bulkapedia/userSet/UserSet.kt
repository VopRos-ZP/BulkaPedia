package com.vopros.bulkapedia.userSet

import com.vopros.bulkapedia.core.Entity

data class UserSet(
    override val id: String,
    val author: String,
    val gears: Map<String, String>,
    val hero: String,
    val liked: List<String>
): Entity(id)
