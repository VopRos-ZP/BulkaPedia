package bulkapedia.categories

import com.google.firebase.firestore.DocumentId

data class CategoryDTO(
    @DocumentId var categoryId: String = "",
    var destination: String = "",
    var enabled: Boolean = false,
    var icon: String = "",
    var title: String = "",
    var subTitle: String = ""
)
