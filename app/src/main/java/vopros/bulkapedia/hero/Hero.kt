package vopros.bulkapedia.hero

import vopros.bulkapedia.core.Entity

data class Hero(
    override val id: String,
    val active: Boolean,
    val difficult: String,
    val image: String,
    val type: String,
    val counterpicks: List<String>,
    val stats: Map<String, Double>
): Entity(id) {

    override fun toData(): Map<String, Any> = mapOf(
        "active" to active,
        "difficult" to difficult,
        "image" to image,
        "type" to type,
        "counterpicks" to counterpicks,
        "stats" to stats,
    )

}
