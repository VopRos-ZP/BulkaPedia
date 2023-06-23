package com.bulkapedia.compose.data.repos.database

import com.bulkapedia.compose.data.labels.Stats
import com.bulkapedia.compose.data.nowTimeFormat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.Exclude
import com.google.firebase.database.PropertyName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @Exclude private var userId: String? = "",
    @PropertyName("email") var email: String,
    @PropertyName("password") var password: String,
    @PropertyName("nickname") var nickname: String,
    @PropertyName("updateEmail") var updateEmail: String = nowTimeFormat(),
    @PropertyName("updateNickname") var updateNickname: String = nowTimeFormat(),
    @PropertyName("mains") var mains: MutableMap<String, Stats>? = null
) {

    constructor() : this("", "", "", "", "", "", null)

    companion object {

        fun DataSnapshot.toUser(): User? {
            return try {
                getValue(User::class.java)
            } catch (_: Exception) { null }
        }

    }

}
