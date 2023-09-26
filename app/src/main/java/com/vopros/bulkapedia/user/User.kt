package com.vopros.bulkapedia.user

import com.google.firebase.Timestamp
import com.vopros.bulkapedia.core.Entity

data class User(
    override val id: String,
    val admin: Boolean,
    val email: String,
    val nickname: String,
    val password: String,
    val updateEmail: Timestamp,
    val updateNickname: Timestamp
): Entity(id) {

    override fun toData(): Map<String, Any> = mapOf(
        "admin" to admin,
        "email" to email,
        "nickname" to nickname,
        "password" to password,
        "updateEmail" to updateEmail,
        "updateNickname" to updateNickname,
    )

}