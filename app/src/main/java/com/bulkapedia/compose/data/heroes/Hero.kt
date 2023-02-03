package com.bulkapedia.compose.data.heroes

import android.os.Parcelable
import com.bulkapedia.compose.data.gears.Gear
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero (
    val heroId: String,
    val name: String,
    val icon: String,
    val type: String,
    val counterpicks: List<String>,
    val personalGears: Map<String, String>,
    val stats: Map<String, Double>,
    val difficult: String
) : Parcelable {

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun DocumentSnapshot.toHero(): Hero? {
            return try {
                Hero(id, getString("name")!!, getString("icon")!!,
                    getString("type")!!, (get("counterpicks") as List<String>),
                    (get("personalGears") as Map<String, String>),
                    (get("stats") as Map<String, Double>), getString("difficult")!!
                )
            } catch (_: Exception) { null }
        }
    }

    override fun toString(): String = "hero"

}

fun String.toGear(gears: List<Gear>): Gear {
    return gears.first { it.icon!! == this }
}
