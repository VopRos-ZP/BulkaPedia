package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.User

interface UserRepository {
    val users: Flow<List<User>>
    suspend fun fetchAll(): List<User>
    suspend fun fetchByEmail(email: String): User
    suspend fun fetchById(id: String): User
    suspend fun upsert(user: User)
    suspend fun delete(user: User)
}
