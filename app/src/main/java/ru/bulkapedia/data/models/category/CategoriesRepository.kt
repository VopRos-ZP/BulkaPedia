package ru.bulkapedia.data.models.category

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bulkapedia.domain.model.Category
import ru.bulkapedia.domain.repository.CategoryRepository
import ru.bulkapedia.domain.utils.Table
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
) : CategoryRepository {

    private val table = Table.CATEGORIES

    private val collection = Firebase.firestore.collection(table)

    override val categories: Flow<List<Category>> = collection
        .snapshots()
        .map { it.toObjects(CategoryDto::class.java).map(CategoryDto::toCategory) }

}