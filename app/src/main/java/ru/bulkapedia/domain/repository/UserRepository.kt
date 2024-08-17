package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.User

interface UserRepository {
    fun listenAll(): Flow<List<User>>
    suspend fun fetchAll(): List<User>
    suspend fun upsert(user: User)
    suspend fun delete(user: User)
}
