package com.bulkapedia.compose.data

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class Map(
    val mapId: String,
    val image: String,
    val imageSpawns: String,
    val name: String,
    val mode: String,
) : Parcelable {

    companion object {
        fun DocumentSnapshot.toMap(): Map? {
            return try {
                Map(id, getString("image")!!, getString("imageSpawns")!!,
                    getString("name")!!, getString("mode")!!)
            } catch (_: Exception) { null }
        }
    }

    override fun toString(): String = "map"

}