package com.vopros.domain.gear

import com.vopros.domain.Entity
import kotlinx.serialization.Serializable
import kotlin.collections.Map

@Serializable
data class Gear(
    var gearId: String = "",
    val gearSet : String = "",
    val icon : String = "",
    var effects : List<Effect> = emptyList(),
    val rankEffect : Map<String, List<Int>> = emptyMap(),
    val cell: String = ""
): Entity() {
    override var id: String
        get() = gearId
        set(value) { gearId = value }

    override fun toData(): Map<String, Any> {
        return mapOf(
            "gearSet" to gearSet,
            "icon" to icon,
            "effects" to effects,
            "ranks" to rankEffect,
            "gearCell" to cell
        )
    }
}
