package ru.bulkapedia.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bulkapedia.data.room.categories.CategoryDatabase
import ru.bulkapedia.data.room.categories.CategoryDto
import ru.bulkapedia.data.room.categories.fromDto
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val categoryDatabase: CategoryDatabase
) : CategoryRepository {

    override fun listenAll(): Flow<List<Category>> = categoryDatabase.dao.listenAll()
        .map { it.map(CategoryDto::fromDto) }



}