package com.bulkapedia.data.sets

import com.bulkapedia.data.asMap
import com.bulkapedia.data.docToObject
import com.google.firebase.firestore.DocumentSnapshot

data class UserSet(
    val userSetId: String,
    val author: String,
    val hero: String,
    val gears: Map<GearCell, String>,
    val userLikeIds: List<String>
) {

    companion object {

        val EMPTY = UserSet(
            "", "", "", mapOf(
                GearCell.HEAD to "empty_head", GearCell.BODY to "empty_body",
                GearCell.ARM to "empty_arm", GearCell.LEG to "empty_leg",
                GearCell.DECOR to "empty_decor", GearCell.DEVICE to "empty_device",
            ), mutableListOf()
        )

        fun DocumentSnapshot.toUserSet(): UserSet? {
            return docToObject(UserSetDTO::class.java) { dto ->
                val gears = mapOf(
                    GearCell.HEAD to dto.head,
                    GearCell.BODY to dto.body,
                    GearCell.ARM to dto.arm,
                    GearCell.LEG to dto.leg,
                    GearCell.DECOR to dto.decor,
                    GearCell.DEVICE to dto.device
                )
                UserSet(id, dto.author, dto.hero, gears, dto.user_like_ids)
            }
        }

        fun UserSet.toData(): Map<String, Any> = asMap().mapValues { it }

    }

}
