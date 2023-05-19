package com.vopros.domain.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    var userId: String = "",
    var email: String = "",
    var password: String = "",
    var nickname: String = "",
    var updateEmail: String? = null,
    var updateNickname: String? = null,
    var mains: MutableMap<String, Stats>? = null
)
