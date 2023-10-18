package vopros.bulkapedia.comment

import com.google.firebase.Timestamp
import vopros.bulkapedia.core.Entity

data class Comment(
    override val id: String,
    val author: String,
    val set: String,
    val text: String,
    val date: Timestamp
): Entity(id) {

    override fun toData(): Map<String, Any> = mapOf(
        "author" to author,
        "set" to set,
        "text" to text,
        "date" to date
    )

}
