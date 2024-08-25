package ru.bulkapedia.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bulkapedia.data.room.user.UserDatabase
import ru.bulkapedia.data.room.user.UserDto
import ru.bulkapedia.data.room.user.fromDto
import ru.bulkapedia.data.room.user.toDto
import ru.bulkapedia.domain.model.User
import ru.bulkapedia.domain.repository.UserRepository

class UserRepositoryImpl(
    private val userDatabase: UserDatabase
) : UserRepository {

    override val users: Flow<List<User>> = userDatabase.dao
        .usersFlow
        .map { it.map(UserDto::fromDto) }

    override suspend fun fetchAll(): List<User> = userDatabase.dao
        .fetchAll()
        .map(UserDto::fromDto)

    override suspend fun fetchByEmail(email: String): User = userDatabase.dao
        .fetchByEmail(email)
        .fromDto()

    override suspend fun fetchById(id: String): User = userDatabase.dao
        .fetchById(id)
        .fromDto()

    override suspend fun upsert(user: User) {
        userDatabase.dao.upsert(user.toDto())
    }

    override suspend fun delete(user: User) {
        userDatabase.dao.delete(user.toDto())
    }

}