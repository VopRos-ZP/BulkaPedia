package com.bulkapedia.compose.data.repos.database

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
    @PropertyName("updateNickname") var updateNickname: String = nowTimeFormat()
) {

    constructor() : this("", "", "", "", "", "")

    companion object {

        fun DataSnapshot.toUser(): User? {
            return try {
                getValue(User::class.java).apply { this?.userId = key }
            } catch (_: Exception) { null }
        }

    }

}
