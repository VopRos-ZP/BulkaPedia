package com.bulkapedia.database

import com.bulkapedia.data.labels.Stats
import java.io.Serializable

data class User(
    var email: String? = null,
    var password: String? = null,
    var nickname: String? = null,
    var mains: MutableMap<String, Stats> = mutableMapOf()
) : Serializable
