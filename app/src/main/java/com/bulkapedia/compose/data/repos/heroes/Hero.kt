package com.bulkapedia.compose.data.repos.heroes

import com.bulkapedia.compose.data.Entity
import com.bulkapedia.compose.data.repos.gears.Gear
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Hero (
    @Transient
    private var heroId: String = "",
    val name: String,
    val icon: String,
    val type: String,
    val counterpicks: List<String>,
    val personalGears: Map<String, String>,
    val stats: Map<String, Double>,
    val difficult: String
): Entity() {

    override var id: String
        get() = heroId
        set(value) { heroId = value }

    override fun toData(): MutableMap<String, Any> {
        return mutableMapOf(
            "name" to name,
            "icon" to icon,
            "type" to type,
            "stats" to stats,
            "difficult" to difficult,
            "counterpicks" to counterpicks,
            "personalGears" to personalGears
        )
    }

    companion object {
        fun DocumentSnapshot.toHero(): Hero? {
            return try {
                Hero(id,
                    get("name") as String,
                    get("icon") as String,
                    get("type") as String,
                    get("counterpicks") as List<String>,
                    get("personalGears") as Map<String, String>,
                    get("stats") as Map<String, Double>,
                    get("difficult") as String
                )
            } catch (_: Exception) { null }
        }
    }

    override fun toString(): String = "hero"

}

fun String.toGear(gears: List<Gear>): Gear {
    return gears.first { it.icon == this }
}
