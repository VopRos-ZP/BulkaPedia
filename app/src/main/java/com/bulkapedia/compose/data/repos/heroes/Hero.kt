package com.bulkapedia.compose.data.repos.heroes

import com.bulkapedia.compose.data.Entity
import com.bulkapedia.compose.data.repos.gears.Gear
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties
import com.google.firebase.firestore.PropertyName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Hero (
    @Transient
    @Exclude
    private var heroId: String = "",
    @PropertyName("name")
    val name: String,
    @PropertyName("icon")
    val icon: String,
    @PropertyName("type")
    val type: String,
    @PropertyName("counterpicks")
    val counterpicks: List<String>,
    @PropertyName("personalGears")
    val personalGears: Map<String, String>,
    @PropertyName("stats")
    val stats: Map<String, Double>,
    @PropertyName("difficult")
    val difficult: String
): Entity() {

    override var id: String
        get() = heroId
        set(value) { heroId = value }

    override fun toData(): MutableMap<String, Any> {
        return Json.decodeFromString(Json.encodeToString(this))
    }

    companion object {
        fun DocumentSnapshot.toHero(): Hero? {
            return try {
                Hero(this.id,
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
