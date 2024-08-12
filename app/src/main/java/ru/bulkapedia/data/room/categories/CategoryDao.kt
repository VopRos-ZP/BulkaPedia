package ru.bulkapedia.data.room.categories

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface CategoryDao {

    @Query("SELECT * FROM categories")
    suspend fun fetchAll(): List<CategoryDto>

    @Upsert
    suspend fun upsert(dto: CategoryDto)

    @Delete
    suspend fun delete(dto: CategoryDto)

}