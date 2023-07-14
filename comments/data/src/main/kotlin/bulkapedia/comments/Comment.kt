package bulkapedia.comments

data class Comment(
    val commentId: String,
    val author: String,
    val setId: String,
    val date: String,
    val text: String
)
