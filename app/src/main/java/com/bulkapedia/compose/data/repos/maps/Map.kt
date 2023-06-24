package com.bulkapedia.compose.data.repos.maps

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Map(
    @Transient
    private var mapId: String = "",
    val image: String,
    val imageSpawns: String,
    val name: String,
    val mode: String,
): Entity() {

    override var id: String
        get() = mapId
        set(value) { mapId = value }

    companion object {
        fun DocumentSnapshot.toMap(): Map? {
            return try {
                Map(id,
                    get("image") as String,
                    get("imageSpawns") as String,
                    get("name") as String,
                    get("mode") as String
                )
            } catch (_: Exception) { null }
        }
    }

    override fun toData(): MutableMap<String, Any> {
        return mutableMapOf(
            "image" to image,
            "imageSpawns" to imageSpawns,
            "name" to name,
            "mode" to mode
        )
    }

    override fun toString(): String = "map"

}