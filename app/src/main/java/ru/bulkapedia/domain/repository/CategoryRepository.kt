package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.Category

interface CategoryRepository {
    fun listenAll(): Flow<List<Category>>
}