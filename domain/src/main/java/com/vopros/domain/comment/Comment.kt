package com.vopros.domain.comment

import com.vopros.domain.Entity
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    var commentId: String = "",
    var set: String = "",
    var from: String = "",
    var text: String = "",
    var date: String = ""
): Entity() {
    override var id: String
        get() = commentId
        set(value) { commentId = value }

    override fun toData(): Map<String, Any> {
        return mapOf(
            "set" to set,
            "from" to from,
            "text" to text,
            "date" to date
        )
    }
}
