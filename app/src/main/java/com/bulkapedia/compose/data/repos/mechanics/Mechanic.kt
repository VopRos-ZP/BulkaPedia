package com.bulkapedia.compose.data.repos.mechanics

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

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
        return Json.decodeFromString(Json.encodeToString(this))
    }

    companion object {
        fun DocumentSnapshot.toMechanic(): Mechanic? {
            return try {
                toObject(Mechanic::class.java).apply { this?.id = id }
            } catch (_: Exception) { null }
        }
    }

}