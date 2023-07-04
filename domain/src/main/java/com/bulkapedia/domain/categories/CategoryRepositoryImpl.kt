package com.bulkapedia.domain.categories

import com.bulkapedia.data.Repository
import com.bulkapedia.data.categories.Category
import com.bulkapedia.data.categories.Category.Companion.toCategory
import com.bulkapedia.data.categories.Category.Companion.toData
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class CategoryRepositoryImpl : RepositoryImpl<Category>(
    Refs.Store.categories,
    { it.toCategory() },
    { it.categoryId }, { it.toData() }
)

typealias CategoryRepository = Repository<Category>