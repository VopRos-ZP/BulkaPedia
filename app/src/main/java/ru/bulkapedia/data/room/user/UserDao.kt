package ru.bulkapedia.data.room.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun listenAll(): Flow<List<UserDto>>

    @Query("SELECT * FROM users")
    suspend fun fetchAll(): List<UserDto>

    @Upsert
    suspend fun upsert(userDto: UserDto)

    @Delete
    suspend fun delete(userDto: UserDto)

}