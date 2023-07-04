package com.bulkapedia.data.hero_info

import com.bulkapedia.data.asMap
import com.bulkapedia.data.docToObject
import com.google.firebase.firestore.DocumentSnapshot

data class HeroInfo(
    val heroInfoId: String,
    val hero: String,
    val description: String,
    val video: String
) {

    companion object {

        fun DocumentSnapshot.toHeroInfo(): HeroInfo? {
            return docToObject(HeroInfoDTO::class.java) { dto ->
                HeroInfo(id, dto.hero, dto.description, dto.video)
            }
        }

        fun HeroInfo.toData(): Map<String, Any> = asMap().mapValues { it }

    }

}