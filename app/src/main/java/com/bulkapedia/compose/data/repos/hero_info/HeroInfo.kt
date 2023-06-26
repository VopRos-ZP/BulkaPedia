package com.bulkapedia.compose.data.repos.hero_info

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class HeroInfo(
    @Transient
    @Exclude
    private var heroInfoId: String = "",
    val hero: String,
    val description: String,
    val video: String
): Entity() {

    override var id: String
        get() = heroInfoId
        set(value) { heroInfoId = value }

    override fun toData(): MutableMap<String, Any> {
        return mutableMapOf(
            "hero" to hero,
            "description" to description,
            "video" to video
        )
    }

    companion object {
        fun DocumentSnapshot.toHeroInfo(): HeroInfo? {
            return try {
                HeroInfo(id,
                    get("hero") as String,
                    get("description") as String,
                    get("video") as String
                )
            } catch (_: Exception) { null }
        }
    }

}