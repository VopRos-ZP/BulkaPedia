package com.vopros.bulkapedia.user

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentId

data class UserDTO(
    @DocumentId var id: String = "",
    var admin: Boolean = false,
    var email: String = "",
    var nickname: String = "",
    var password: String = "",
    var updateEmail: Timestamp = Timestamp.now(),
    var updateNickname: Timestamp = Timestamp.now()
)
