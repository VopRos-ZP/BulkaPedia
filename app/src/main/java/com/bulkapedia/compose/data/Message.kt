package com.bulkapedia.compose.data

import android.os.Parcelable
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(
    val author: String,
    val date: String,
    val image: String,
    val receiver: String,
    val text: String,
    val read: Boolean
): Parcelable {

    companion object {
        fun DocumentSnapshot.toMessage(): Message? {
            return try {
                Message(
                    getString("author")!!, getString("date")!!,
                    getString("image")!!, getString("receiver")!!,
                    getString("text")!!, getBoolean("read")!!,
                )
            } catch (_: Exception) { null }
        }
    }

}
