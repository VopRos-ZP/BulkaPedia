package com.bulkapedia.compose.data

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize
import kotlin.collections.Map

@Parcelize
data class DBEffect(
    val id: String,
    val ru: String
) : Parcelable {

    companion object {
        fun DocumentSnapshot.toEffect() : DBEffect? {
            return try {
                DBEffect(id, getString("ru")!!)
            } catch (_: Exception) { null }
        }

        fun DBEffect.getData(): Map<String, Any> = mapOf("ru" to ru)

    }

}