package com.bulkapedia.data.heroes

import com.bulkapedia.data.asMap
import com.bulkapedia.data.docToObject
import com.google.firebase.firestore.DocumentSnapshot

data class Hero(
    val heroId: String,
    val name: String,
    val icon: String,
    val type: String,
    val counterpicks: List<String>,
    val personalGears: Map<String, String>,
    val stats: Map<String, Double>,
    val difficult: String
) {

    companion object {

        fun DocumentSnapshot.toHero(): Hero? {
            return docToObject(HeroDTO::class.java) { dto ->
                Hero(id, dto.name,
                    dto.icon, dto.type,
                    dto.counterpicks,
                    dto.personalGears,
                    dto.stats, dto.difficult
                )
            }
        }

        fun Hero.toData(): Map<String, Any> = asMap().mapValues { it }

    }

}
