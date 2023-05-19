package com.vopros.domain.mechanics

import com.vopros.domain.Entity
import kotlinx.serialization.Serializable

@Serializable
data class Mechanic(
    var mechanicId: String = "",
    var title: String = "",
    var video: String = "",
    var icon: String = "",
    var description: String = ""
): Entity() {
    override var id: String
        get() = mechanicId
        set(value) { mechanicId = value }

    override fun toData(): Map<String, Any> {
        return mapOf(
            "title" to title,
            "video" to video,
            "icon" to icon,
            "description" to description
        )
    }
}
