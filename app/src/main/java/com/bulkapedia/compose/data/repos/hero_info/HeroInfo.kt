package com.bulkapedia.compose.data.repos.hero_info

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class HeroInfo(
    @Transient
    private var heroInfoId: String = "",
    val hero: String,
    val description: String,
    val video: String
): Entity() {

    override var id: String
        get() = heroInfoId
        set(value) { heroInfoId = value }

    override fun toData(): MutableMap<String, Any> {
        return Json.decodeFromString(Json.encodeToString(this))
    }

    companion object {
        fun DocumentSnapshot.toHeroInfo(): HeroInfo? {
            return try {
                toObject(HeroInfo::class.java).apply { this?.id = id }
            } catch (_: Exception) { null }
        }
    }

}