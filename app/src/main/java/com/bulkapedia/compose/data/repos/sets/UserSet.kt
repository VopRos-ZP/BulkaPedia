package com.bulkapedia.compose.data.repos.sets

import com.bulkapedia.compose.data.Entity
import com.bulkapedia.compose.data.repos.gears.longToInt
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class UserSet(
    @Transient
    private var userSetId: String = "",
    var from: String,
    val hero: String,
    var gears: Map<GearCell, String>,
    var likes: Int,
    var userLikeIds: MutableList<String>
): Entity() {

    override var id: String
        get() = userSetId
        set(value) { userSetId = value }

    override fun toData(): MutableMap<String, Any> {
        return mutableMapOf(
            "from" to from,
            "hero" to hero,
            "likes" to likes,
            "head" to gears.getValue(GearCell.HEAD),
            "body" to gears.getValue(GearCell.BODY),
            "arm" to gears.getValue(GearCell.ARM),
            "leg" to gears.getValue(GearCell.LEG),
            "decor" to gears.getValue(GearCell.DECOR),
            "device" to gears.getValue(GearCell.DEVICE),
            "userLikeIds" to userLikeIds
        )
    }

    companion object {
        val EMPTY = UserSet(
            "", "", "", mapOf(
                GearCell.HEAD to "empty_head", GearCell.BODY to "empty_body",
                GearCell.ARM to "empty_arm", GearCell.LEG to "empty_leg",
                GearCell.DECOR to "empty_decor", GearCell.DEVICE to "empty_device",
            ), 0, mutableListOf()
        )

        fun DocumentSnapshot.toUserSet(): UserSet? {
            return try {
                val gears = mapOf(
                    GearCell.HEAD to getString("head")!!, GearCell.BODY to getString("body")!!,
                    GearCell.ARM to getString("arm")!!, GearCell.LEG to getString("leg")!!,
                    GearCell.DECOR to getString("decor")!!, GearCell.DEVICE to getString("device")!!,
                )
                UserSet(id, getString("author")!!, getString("hero")!!,
                    gears, longToInt(getLong("likes")!!),
                    (get("user_like_ids") as MutableList<String>)
                )
            } catch (_:Exception) { null }
        }
    }

}
