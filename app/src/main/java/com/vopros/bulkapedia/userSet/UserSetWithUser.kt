package com.vopros.bulkapedia.userSet

import com.vopros.bulkapedia.user.User

data class UserSetWithUser(
    val id: String,
    val author: User,
    val gears: Map<String, String>,
    val hero: String,
    val liked: List<String>
)