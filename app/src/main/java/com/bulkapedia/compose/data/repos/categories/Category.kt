package com.bulkapedia.compose.data.repos.categories

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Exclude
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Category(
    @Transient
    @Exclude
    private var categoryId: String = "",
    val title: String,
    val subTitle: String,
    val enabled: Boolean,
    val destination: String,
    val icon: String
): Entity() {

    override var id: String
        get() = categoryId
        set(value) { categoryId = value }

    override fun toData(): MutableMap<String, Any> {
        return Json.decodeFromString(Json.encodeToString(this))
    }

    companion object {
        fun DocumentSnapshot.toCategory(): Category? {
            return try {
                Category(id,
                    get("title") as String,
                    get("subTitle") as String,
                    get("enabled") as Boolean,
                    get("destination") as String,
                    get("icon") as String
                )
            } catch (_: Exception) { null }
        }
    }

}