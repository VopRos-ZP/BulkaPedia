package com.bulkapedia.compose.data.category

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class HeroInfo(
    val id: String,
    val hero: String,
    val description: String,
    val video: String
) : Parcelable {

    companion object {
        fun DocumentSnapshot.toHeroInfo(): HeroInfo? {
            return try {
                HeroInfo(
                    id,
                    getString("hero")!!,
                    getString("description")!!,
                    getString("video")!!
                )
            } catch (_: Exception) { null }
        }
    }

}