package ru.bulkapedia.domain.usecase.category

import ru.bulkapedia.data.room.categories.CategoryDatabase
import ru.bulkapedia.data.room.categories.toDto
import ru.bulkapedia.domain.model.Category

class UpsertCategoryUseCase(
    private val categoryDatabase: CategoryDatabase
) {

    suspend operator fun invoke(categories: List<Category>) {
        categories.forEach {
            categoryDatabase.dao.upsert(it.toDto())
        }
    }

}