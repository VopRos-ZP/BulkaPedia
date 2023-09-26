package com.vopros.bulkapedia.category

import com.vopros.bulkapedia.core.Entity

data class Category(
    override val id: String,
    val title: String,
    val subTitle: String,
    val enabled: Boolean,
    val icon: String,
    val destination: String,
): Entity(id) {

    override fun toData(): Map<String, Any> = mapOf(
        "title" to title,
        "subTitle" to subTitle,
        "enabled" to enabled,
        "icon" to icon,
        "destination" to destination,
    )

}