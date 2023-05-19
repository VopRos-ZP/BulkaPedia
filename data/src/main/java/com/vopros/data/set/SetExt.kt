package com.vopros.data.set

import com.google.firebase.firestore.DocumentSnapshot
import com.vopros.domain.set.GearCell
import com.vopros.domain.set.Set

fun DocumentSnapshot.toSet(): Set? {
    return try {
        val gears = mapOf(
            GearCell.HEAD to getString("head")!!,
            GearCell.BODY to getString("body")!!,
            GearCell.ARM to getString("arm")!!,
            GearCell.LEG to getString("leg")!!,
            GearCell.DECOR to getString("decor")!!,
            GearCell.DEVICE to getString("device")!!
        )
        Set(
            id, getString("from")!!,
            getString("hero")!!, gears,
            getLong("likes")!!.toInt(),
            get("user_like_ids") as MutableList<String>
        )
    } catch (_: Exception) { null }
}
