package com.bulkapedia.data.mains

import com.bulkapedia.data.asMap
import com.bulkapedia.data.docToObject
import com.google.firebase.firestore.DocumentSnapshot

data class Main(
    val mainId: String,
    val kills: Int,
    val winrate: Double,
    val revives: Int
) {

    companion object {

        fun DocumentSnapshot.toMain(): Main? {
            return docToObject(MainDTO::class.java) { dto ->
                Main(id, dto.kills, dto.winrate, dto.revives)
            }
        }

        fun Main.toData(): Map<String, Any> = asMap().mapValues { it }

    }

}
