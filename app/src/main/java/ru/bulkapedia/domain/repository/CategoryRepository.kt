package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.Category

interface CategoryRepository {
    val categories: Flow<List<Category>>
}