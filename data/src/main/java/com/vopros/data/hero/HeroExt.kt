package com.vopros.data.hero

import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.domain.gear.Gear
import com.vopros.domain.hero.Hero

fun DocumentSnapshot.toHero(): Hero? {
    return try {
        Hero(id, getString("name")!!, getString("icon")!!,
            getString("type")!!, (get("counterpicks") as List<String>),
            (get("personalGears") as Map<String, String>),
            (get("stats") as Map<String, Double>), getString("difficult")!!
        )
    } catch (_: Exception) { null }
}

fun String.toGear(gears: List<Gear>): Gear {
    return gears.first { it.icon == this }
}

fun Hero.toData(): Map<String, Any> {
    return mapOf(
        "name" to name,
        "icon" to icon,
        "type" to type,
        "counterpicks" to counterpicks,
        "personalGears" to personalGears,
        "stats" to stats,
        "difficult" to difficult
    )
}