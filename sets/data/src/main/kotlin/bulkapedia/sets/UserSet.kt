package bulkapedia.sets

import bulkapedia.gears.GearCell

data class UserSet(
    val setId: String,
    val author: String,
    val hero: String,
    val gears: Map<GearCell, String>,
    val userLikeIds: List<String>
) {

    companion object {
        val EMPTY = UserSet("", "", "", emptyMap(), emptyList())
    }

}
