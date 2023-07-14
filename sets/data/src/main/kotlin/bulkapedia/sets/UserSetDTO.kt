package bulkapedia.sets

import com.google.firebase.firestore.DocumentId

data class UserSetDTO(
    @DocumentId var setId: String = "",
    var author: String = "",
    var hero: String = "",
    var head: String = "",
    var body: String = "",
    var arm: String = "",
    var leg: String = "",
    var decor: String = "",
    var device: String = "",
    var userLikeIds: List<String> = emptyList()
)