package ru.bulkapedia.domain.usecase.category

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bulkapedia.data.room.categories.CategoryDatabase
import ru.bulkapedia.data.room.categories.CategoryDto
import ru.bulkapedia.data.room.categories.fromDto
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.domain.repository.CategoryRepository
import ru.bulkapedia.domain.usecase.BaseUseCase

class GetAllCategoryUseCase(
    private val categoryRepository: CategoryRepository
) : BaseUseCase() {

    operator fun invoke(): Flow<List<Category>> =
        categoryRepository.listenAll().stateIn()

}