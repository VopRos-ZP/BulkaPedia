package com.vopros.bulkapedia.user

import com.google.firebase.Timestamp
import com.vopros.bulkapedia.core.Entity

data class User(
    override val id: String,
    val admin: Boolean,
    val email: String,
    val nickname: String,
    val password: String,
    val updateEmail: String, // Timestamp
    val updateNickname: String // Timestamp
): Entity(id)