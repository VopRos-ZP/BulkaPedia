package ru.bulkapedia.di

import ru.bulkapedia.data.models.category.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.bulkapedia.domain.repository.CategoryRepository

@Module
@InstallIn(SingletonComponent::class)
interface FirebaseModule {

    @Binds
    fun bindCategoryRepository(categoriesRepository: CategoriesRepository): CategoryRepository

}