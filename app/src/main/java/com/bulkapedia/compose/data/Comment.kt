package com.bulkapedia.compose.data

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comment(
    val set: String,
    val from: String,
    val text: String,
    val date: String
): Parcelable {

    companion object {
        fun DocumentSnapshot.toComment(): Comment? {
            return try {
                Comment(
                    getString("set")!!, getString("from")!!,
                    getString("text")!!, getString("date")!!,
                )
            } catch (_: Exception) { null }
        }
    }

}
