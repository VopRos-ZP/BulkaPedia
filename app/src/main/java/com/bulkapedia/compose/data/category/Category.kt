package com.bulkapedia.compose.data.category

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val title: String,
    val subTitle: String,
    val enabled: Boolean,
    val destination: String,
    val icon: String
) : Parcelable {

    companion object {
        fun DocumentSnapshot.toCategory(): Category? {
            return try {
                Category(
                    title = getString("title")!!,
                    subTitle = getString("subTitle")!!,
                    enabled = getBoolean("enabled")!!,
                    destination = getString("destination")!!,
                    icon = getString("icon")!!
                )
            } catch (_: Exception) { null }
        }
    }

}