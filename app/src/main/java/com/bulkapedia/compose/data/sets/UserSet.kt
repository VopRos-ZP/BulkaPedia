package com.bulkapedia.compose.data.sets

import android.os.Parcelable
import com.bulkapedia.compose.data.gears.longToInt
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserSet(
    var userSetId: String,
    val from: String,
    val hero: String,
    var gears: Map<GearCell, String>,
    var likes: Int,
    var userLikeIds: MutableList<String>
) : Parcelable {

    companion object {
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
