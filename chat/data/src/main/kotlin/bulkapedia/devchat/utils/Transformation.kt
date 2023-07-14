package bulkapedia.devchat.utils

import bulkapedia.devchat.Message
import bulkapedia.devchat.MessageDTO

fun Message.toDTO(): MessageDTO = MessageDTO(messageId, author, receiver, date, text, image, read)

fun MessageDTO.toPOJO(): Message = Message(messageId, author, receiver, date, text, image, read)