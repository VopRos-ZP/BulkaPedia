package com.bulkapedia.compose.data.repos.categories

import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Category(
    @Transient
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
                toObject(Category::class.java).apply { this?.id = id }
            } catch (_: Exception) { null }
        }
    }

}