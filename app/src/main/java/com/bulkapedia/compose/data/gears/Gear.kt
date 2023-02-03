package com.bulkapedia.compose.data.gears

import android.os.Parcelable
import com.bulkapedia.compose.data.labels.Ranks
import com.bulkapedia.compose.util.autoFillGearEffects
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gear(
    val gearSet : String,
    val icon : String,
    var effects : List<Effect>,
    val rankEffect : Map<String, List<Int>>,
    val name: String
) : Parcelable {

    companion object {

        fun DocumentSnapshot.toGear(): Gear? {
            return try {
                val effects = (data?.get("effects")!! as Map<String, Map<String, Any>>).toEffects()
                Gear(
                    getString("gearSet")!!, id, effects,
                    (get("ranks") as Map<String, List<Int>>), id
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
