package bulkapedia.instances

import bulkapedia.categories.Category
import bulkapedia.categories.CategoryDTO
import bulkapedia.categories.utils.toDTO
import bulkapedia.categories.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class CategoryRepositoryImpl(ref: CollectionReference) : RepositoryImpl<Category>(
    ref, { it.toObject(CategoryDTO::class.java)?.toPOJO() },
    { it.categoryId }, { it.toDTO() }
)