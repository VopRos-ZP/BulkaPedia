package com.vopros.data.gear

import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.data.util.autoFillGearEffects
import com.vopros.domain.Ranks
import com.vopros.domain.gear.Effect
import com.vopros.domain.gear.Gear

fun DocumentSnapshot.toGear(): Gear? {
    return try {
        val effects = (data?.get("effects")!! as Map<String, Map<String, Any>>).toEffects()
        Gear(id, getString("gearSet")!!, getString("icon")!!, effects,
            (get("ranks") as Map<String, List<Int>>), getString("gearCell")!!
        )
    } catch (_: Exception) { null }
}

fun Map<String, Map<String, Any>>.toEffects(): List<Effect> {
    val res = mutableListOf<Effect>()
    forEach { (_, map) ->
        res.add(
            Effect(
                (map["number"] as Long).toInt(),
                map["percent"] as Boolean,
                map["description"] as String
            )
        )
    }
    return res
}

fun Gear.getRankEffect(): Map<Ranks, List<Effect>> {
    val rankEffects = rankEffect.mapKeys { it.key.toInt() }
    return autoFillGearEffects(effects, rankEffects)
}

fun Gear.toData(): Map<String, Any> {
    return mapOf(
        "gearSet" to gearSet,
        "icon" to icon,
        "effects" to effects,
        "ranks" to rankEffect,
        "gearCell" to cell
    )
}
