package com.vopros.domain.heroinfo

import com.vopros.domain.Entity
import kotlinx.serialization.Serializable

@Serializable
data class HeroInfo(
    var heroInfoId: String = "",
    var hero: String = "",
    var video: String = "",
    var description: String = ""
): Entity() {
    override var id: String
        get() = heroInfoId
        set(value) { heroInfoId = value }

    override fun toData(): Map<String, Any> {
        return mapOf(
            "hero" to hero,
            "video" to video,
            "description" to description
        )
    }
}
