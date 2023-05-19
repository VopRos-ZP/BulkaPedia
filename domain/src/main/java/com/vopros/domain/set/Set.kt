package com.vopros.domain.set

import com.vopros.domain.Entity
import kotlinx.serialization.Serializable

@Serializable
data class Set(
    var setId: String = "",
    var from: String = "",
    val hero: String = "",
    var gears: Map<GearCell, String> = emptyMap(),
    var likes: Int = 0,
    var userLikeIds: MutableList<String> = mutableListOf()
) : Entity() {
    override var id: String
        get() = setId
        set(value) { setId = value }

    override fun toData(): Map<String, Any> {
        return mapOf(
            "author" to from,
            "hero" to hero,
            "head" to gears.getValue(GearCell.HEAD),
            "body" to gears.getValue(GearCell.BODY),
            "arm" to gears.getValue(GearCell.ARM),
            "leg" to gears.getValue(GearCell.LEG),
            "decor" to gears.getValue(GearCell.DECOR),
            "device" to gears.getValue(GearCell.DEVICE),
            "likes" to likes,
            "user_like_ids" to userLikeIds
        )
    }
}
