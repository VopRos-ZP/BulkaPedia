package com.bulkapedia.compose.data.repos.gears

import com.bulkapedia.compose.data.Entity
import com.bulkapedia.compose.data.labels.Ranks
import com.bulkapedia.compose.data.repos.sets.GearCell
import com.bulkapedia.compose.screens.createset.emptyGears
import com.bulkapedia.compose.util.autoFillGearEffects
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Gear(
    @Transient
    private var gearId: String = "",
    val gearSet : String,
    val icon : String,
    val effects : List<Effect>,
    val rankEffect : Map<String, List<Int>>,
    val cell: String
): Entity() {

    override var id: String
        get() = gearId
        set(value) { gearId = value }

    override fun toData(): MutableMap<String, Any> {
        return mutableMapOf(
            "gearSet" to gearSet,
            "gearCell" to cell,
            "icon" to icon,
            "effects" to effects,
            "ranks" to rankEffect,
        )
    }

    companion object {

        val EMPTY: (GearCell) -> Gear = { cell ->
            Gear("", "", emptyGears[cell]!!, emptyList(), emptyMap(), cell.name)
        }

        fun DocumentSnapshot.toGear(): Gear? {
            return try {
                Gear(id,
                    get("gearSet") as String,
                    get("icon") as String,
                    (get("effects") as Map<String, Map<String, Any>>).toEffects(),
                    get("ranks") as Map<String, List<Int>>,
                    get("gearCell") as String
                )
            } catch (_: Exception) { null }
        }

    }

}

fun Map<String, Map<String, Any>>.toEffects(): List<Effect> {
    val res = mutableListOf<Effect>()
    forEach { (_, map) ->
        res.add(Effect(
            longToInt(map["number"] as Long),
            map["percent"] as Boolean,
            map["description"] as String
        ))
    }
    return res
}

fun longToInt(l: Long): Int {
    return if (l <= Int.MAX_VALUE) Integer.parseInt(l.toString())
    else 0
}

fun Gear.getRankEffect(): Map<Ranks, List<Effect>> {
    val rankEffects = rankEffect.mapKeys { it.key.toInt() }
    return autoFillGearEffects(effects, rankEffects)
}
