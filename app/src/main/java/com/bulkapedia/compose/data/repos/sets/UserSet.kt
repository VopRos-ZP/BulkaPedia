package com.bulkapedia.compose.data.repos.sets

import androidx.compose.runtime.Stable
import com.bulkapedia.compose.data.Entity
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable

@Serializable
data class UserSet(
    private var userSetId: String = "",
    var from: String,
    val hero: String,
    var gears: Map<GearCell, String>,
    var userLikeIds: List<String>
): Entity() {

    override var id: String
        get() = userSetId
        set(value) { userSetId = value }

    override fun toData(): MutableMap<String, Any> {
        return mutableMapOf(
            "author" to from,
            "hero" to hero,
            "head" to gears.getValue(GearCell.HEAD),
            "body" to gears.getValue(GearCell.BODY),
            "arm" to gears.getValue(GearCell.ARM),
            "leg" to gears.getValue(GearCell.LEG),
            "decor" to gears.getValue(GearCell.DECOR),
            "device" to gears.getValue(GearCell.DEVICE),
            "user_like_ids" to userLikeIds
        )
    }

    companion object {
        val EMPTY = UserSet(
            "", "", "", mapOf(
                GearCell.HEAD to "empty_head", GearCell.BODY to "empty_body",
                GearCell.ARM to "empty_arm", GearCell.LEG to "empty_leg",
                GearCell.DECOR to "empty_decor", GearCell.DEVICE to "empty_device",
            ), mutableListOf()
        )

        fun DocumentSnapshot.toUserSet(): UserSet? {
            return try {
                val gears = mapOf(
                    GearCell.HEAD to getString("head")!!, GearCell.BODY to getString("body")!!,
                    GearCell.ARM to getString("arm")!!, GearCell.LEG to getString("leg")!!,
                    GearCell.DECOR to getString("decor")!!, GearCell.DEVICE to getString("device")!!,
                )
                val ids = get("user_like_ids") as List<String>
                UserSet(id, getString("author")!!, getString("hero")!!, gears, ids)
            } catch (_:Exception) { null }
        }
    }

}
