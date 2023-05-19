package com.vopros.domain.hero

import com.vopros.domain.Entity
import kotlinx.serialization.Serializable

@Serializable
data class Hero(
    var heroId: String = "",
    var name: String = "",
    var icon: String = "",
    var type: String = "",
    var counterpicks: List<String> = emptyList(),
    var personalGears: Map<String, String> = emptyMap(),
    var stats: Map<String, Double> = emptyMap(),
    var difficult: String = ""
): Entity() {
    override var id: String
        get() = heroId
        set(value) { heroId = value }

    override fun toData(): Map<String, Any> {
        return mapOf(
            "name" to name,
            "icon" to icon,
            "type" to type,
            "counterpicks" to counterpicks,
            "personalGears" to personalGears,
            "stats" to stats,
            "difficult" to difficult,
        )
    }
}