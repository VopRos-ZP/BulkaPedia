package com.bulkapedia.data.maps

import com.bulkapedia.data.asMap
import com.bulkapedia.data.docToObject
import com.google.firebase.firestore.DocumentSnapshot


data class Map(
    val mapId: String,
    val image: String,
    val imageSpawns: String,
    val name: String,
    val mode: String
) {

    companion object {

        fun DocumentSnapshot.toMap(): Map? {
            return docToObject(MapDTO::class.java) { dto ->
                Map(id, dto.image, dto.imageSpawns, dto.name, dto.mode)
            }
        }

        fun Map.toData(): kotlin.collections.Map<String, Any> = asMap().mapValues { it }

    }

}
