package com.bulkapedia.compose.data.category

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class Mechanic(
    val id: String,
    val title: String,
    val video: String,
    val description: String,
    val icon: String
) : Parcelable {

    companion object {
        fun DocumentSnapshot.toMechanic(): Mechanic? {
            return try {
                Mechanic(id,
                    getString("title")!!,
                    getString("video")!!,
                    getString("description")!!,
                    getString("icon")!!
                )
            } catch (_: Exception) { null }
        }
    }

}