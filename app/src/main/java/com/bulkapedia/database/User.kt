package com.bulkapedia.database

import java.io.Serializable

data class User(
    var login: String? = null,
    var password: String? = null,
    var nickname: String? = null
) : Serializable
