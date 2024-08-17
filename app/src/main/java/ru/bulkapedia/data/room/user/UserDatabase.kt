package ru.bulkapedia.data.room.user

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserDto::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract val dao: UserDao

    companion object {
        const val NAME = "user"
    }
}