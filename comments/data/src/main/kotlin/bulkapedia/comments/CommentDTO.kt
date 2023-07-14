package bulkapedia.comments

import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class CommentDTO(
    @DocumentId var id: String = "",
    @PropertyName("from") var author: String = "",
    @PropertyName("set") var setId: String = "",
    @PropertyName("date") var date: String = "",
    @PropertyName("text") var text: String = ""
)
