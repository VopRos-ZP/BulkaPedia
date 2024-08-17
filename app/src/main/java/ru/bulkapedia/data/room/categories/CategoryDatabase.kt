package ru.bulkapedia.data.room.categories

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CategoryDto::class],
    version = 1
)
abstract class CategoryDatabase : RoomDatabase() {
    abstract val dao: CategoryDao

    companion object {
        const val NAME = "category"
    }
}