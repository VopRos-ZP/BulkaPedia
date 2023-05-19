package com.vopros.data.category

import com.vopros.data.Database
import com.vopros.data.RepositoryImpl
import com.vopros.domain.categories.Category

class CategoryRepositoryImpl : RepositoryImpl<Category>(
    Database.categories, { it.toCategory() }, "Категория не найдена!"
)