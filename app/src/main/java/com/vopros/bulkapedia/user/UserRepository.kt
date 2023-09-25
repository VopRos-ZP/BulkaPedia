package com.vopros.bulkapedia.user

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vopros.bulkapedia.firebase.FirebaseImpl
import com.vopros.bulkapedia.firebase.toUser
import javax.inject.Inject

class UserRepository @Inject constructor(): FirebaseImpl<User>(
    Firebase.firestore.collection("users"), ::toUser
)