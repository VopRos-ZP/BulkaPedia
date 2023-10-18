package vopros.bulkapedia.comment

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class CommentDTO(
    @DocumentId var id: String = "",
    var author: String = "",
    var set: String = "",
    var text: String = "",
    var date: Timestamp = Timestamp.now()
)
