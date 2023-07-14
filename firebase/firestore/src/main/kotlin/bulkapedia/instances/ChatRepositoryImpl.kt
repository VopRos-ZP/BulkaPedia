package bulkapedia.instances

import bulkapedia.devchat.Message
import bulkapedia.devchat.MessageDTO
import bulkapedia.devchat.utils.toDTO
import bulkapedia.devchat.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class ChatRepositoryImpl(ref: CollectionReference) : RepositoryImpl<Message>(
    ref, { it.toObject(MessageDTO::class.java)?.toPOJO() },
    { it.messageId }, { it.toDTO() }
)