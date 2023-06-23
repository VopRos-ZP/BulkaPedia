package com.bulkapedia.compose.data.repos.categories

import com.bulkapedia.compose.data.repos.categories.Category.Companion.toCategory
import com.bulkapedia.compose.data.repos.firestore.FirestoreDB
import com.bulkapedia.compose.data.repos.firestore.FirestoreRepository
import com.bulkapedia.compose.data.repos.firestore.Repository

class CategoriesRepositoryImpl : FirestoreRepository<Category>(FirestoreDB.categories, { it.toCategory() })

typealias CategoriesRepository = Repository<Category>