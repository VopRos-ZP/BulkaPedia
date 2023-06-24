package com.bulkapedia.compose.data.repos.mechanics

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Mechanic(
    @Transient
    private var mechanicId: String = "",
    val title: String,
    val video: String,
    val description: String,
    val icon: String
): Entity() {

    override var id: String
        get() = mechanicId
        set(value) { mechanicId = value }

    override fun toData(): MutableMap<String, Any> {
        return mutableMapOf(
            "title" to title,
            "description" to description,
            "video" to video,
            "icon" to icon
        )
    }

    companion object {
        fun DocumentSnapshot.toMechanic(): Mechanic? {
            return try {
                Mechanic(id,
                    get("title") as String,
                    get("video") as String,
                    get("description") as String,
                    get("icon") as String
                )
            } catch (_: Exception) { null }
        }
    }

}