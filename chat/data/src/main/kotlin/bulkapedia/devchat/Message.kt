package bulkapedia.devchat

data class Message(
    val messageId: String,
    val author: String,
    val receiver: String,
    val date: String,
    val text: String,
    val image: String,
    val read: Boolean
)
