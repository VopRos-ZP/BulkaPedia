package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.User

interface UserRepository {
    suspend fun fetchAll(): List<User>
    suspend fun fetchById(id: String): User
    suspend fun upsert(user: User)
    suspend fun delete(id: String)

    suspend fun listenAll(): Flow<List<User>>
    suspend fun listenById(id: String): Flow<User>
    suspend fun subscribe()
    suspend fun dispose()
}