package com.vopros.bulkapedia.firebase

import com.vopros.bulkapedia.user.User

interface AuthRepository {
    suspend fun sendPasswordResetCode(email: String, onError: (String) -> Unit)
    suspend fun register(user: User, onError: (String) -> Unit, onSuccess: (User) -> Unit)
    suspend fun login(email: String, password: String, onError: (String) -> Unit, onSuccess: (User) -> Unit)
    fun logout()
}