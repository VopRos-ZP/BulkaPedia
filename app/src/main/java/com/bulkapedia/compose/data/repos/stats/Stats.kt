package com.bulkapedia.compose.data.repos.stats

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Stats(
    @Transient
    private var statsId: String = "",
    val kills: Int,
    val winrate: Double,
    val revives: Int
): Entity() {

    override var id: String
        get() = statsId
        set(value) { statsId = value }

    override fun toData(): MutableMap<String, Any> {
        return mutableMapOf(
            "kills" to kills,
            "winrate" to winrate,
            "revives" to revives
        )
    }

    companion object {

        fun DocumentSnapshot.toStats(): Stats? {
            return try {
                Stats(id,
                    (get("kills") as Long).toInt(),
                    get("winrate") as Double,
                    (get("revives") as Long).toInt()
                )
            } catch (_: Exception) { null }
        }

    }

}
