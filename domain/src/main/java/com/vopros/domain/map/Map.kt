package com.vopros.domain.map

import com.vopros.domain.Entity
import kotlinx.serialization.Serializable
import kotlin.collections.Map

@Serializable
data class Map(
    var mapId: String = "",
    var image: String = "",
    var imageSpawns: String = "",
    var name: String = "",
    var mode: String = ""
): Entity() {
    override var id: String
        get() = mapId
        set(value) { mapId = value }

    override fun toData(): Map<String, Any> {
        return mapOf(
            "image" to image,
            "imageSpawns" to imageSpawns,
            "name" to name,
            "mode" to mode
        )
    }

}