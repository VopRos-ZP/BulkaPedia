package ru.bulkapedia.data.room.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @get:Query("SELECT * FROM users")
    val usersFlow: Flow<List<UserDto>>

    @Query("SELECT * FROM users")
    suspend fun fetchAll(): List<UserDto>

    @Query("SELECT * FROM users WHERE id = :id")
    suspend fun fetchById(id: String): UserDto

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun fetchByEmail(email: String): UserDto

    @Upsert
    suspend fun upsert(userDto: UserDto)

    @Delete
    suspend fun delete(userDto: UserDto)

}