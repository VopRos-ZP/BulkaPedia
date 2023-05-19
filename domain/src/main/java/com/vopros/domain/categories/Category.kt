package com.vopros.domain.categories

import com.vopros.domain.Entity
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    var categoryId: String = "",
    var destination: String = "",
    var enabled: Boolean = false,
    var icon: String = "",
    var title: String = "",
    var subTitle: String = ""
): Entity() {
    override var id: String
        get() = categoryId
        set(value) { categoryId = value }

    override fun toData(): Map<String, Any> {
        return mapOf(
            "destination" to destination,
            "enabled" to enabled,
            "icon" to icon,
            "title" to title,
            "subTitle" to subTitle
        )
    }
}
