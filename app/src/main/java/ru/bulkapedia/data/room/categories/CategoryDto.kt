package ru.bulkapedia.data.room.categories

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    @ColumnInfo("is_enabled")
    val isEnabled: Boolean,
    @ColumnInfo("is_visible")
    val isVisible: Boolean,
    val route: String
)
