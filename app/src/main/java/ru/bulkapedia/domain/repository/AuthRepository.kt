package ru.bulkapedia.domain.repository

interface AuthRepository {
    suspend fun signIn(email: String, password: String)
    suspend fun signUp(email: String, password: String): String?
    suspend fun logout()
}