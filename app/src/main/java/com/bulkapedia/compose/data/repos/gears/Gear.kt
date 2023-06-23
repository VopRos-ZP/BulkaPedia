package com.bulkapedia.compose.data.repos.gears

import com.bulkapedia.compose.data.Entity
import com.bulkapedia.compose.data.labels.Ranks
import com.bulkapedia.compose.util.autoFillGearEffects
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Gear(
    @Transient
    private var gearId: String = "",
    val gearSet : String,
    val icon : String,
    var effects : List<Effect>,
    val rankEffect : Map<String, List<Int>>,
    val name: String,
    val cell: String
): Entity() {

    override var id: String
        get() = gearId
        set(value) { gearId = value }

    override fun toData(): MutableMap<String, Any> {
        return Json.decodeFromString(Json.encodeToString(this))
    }

    companion object {

        fun DocumentSnapshot.toGear(): Gear? {
            return try {
                val effects = (get("effects")!! as Map<String, Map<String, Any>>).toEffects()
                Gear(id, getString("gearSet")!!, getString("icon")!!, effects,
                    (get("ranks") as Map<String, List<Int>>), id, getString("gearCell")!!
                )
            } catch (_: Exception) { null }
        }

    }

}

fun Map<String, Map<String, Any>>.toEffects(): List<Effect> {
    val res = mutableListOf<Effect>()
    forEach { (_, map) ->
        res.add(
            Effect(
            longToInt(map["number"] as Long),
            map["percent"] as Boolean,
            map["description"] as String
        )
        )
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