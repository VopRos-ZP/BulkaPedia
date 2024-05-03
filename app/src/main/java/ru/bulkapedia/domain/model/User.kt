package ru.bulkapedia.domain.model

import android.os.Parcelable
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @DocumentId
    val id: String,
    val nickname: String,
    @PropertyName("is_admin")
    val isAdmin: Boolean = false
) : Parcelable
