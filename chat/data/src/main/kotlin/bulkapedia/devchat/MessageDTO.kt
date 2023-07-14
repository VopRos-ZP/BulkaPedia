package bulkapedia.devchat

import com.google.firebase.firestore.DocumentId

data class MessageDTO(
    @DocumentId var messageId: String = "",
    var author: String = "",
    var receiver: String = "",
    var date: String = "",
    var text: String = "",
    var image: String = "",
    var read: Boolean = false
)
