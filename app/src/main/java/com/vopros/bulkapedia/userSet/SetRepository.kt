package com.vopros.bulkapedia.userSet

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vopros.bulkapedia.firebase.FirebaseImpl
import com.vopros.bulkapedia.firebase.toUserSet
import javax.inject.Inject

class SetRepository @Inject constructor() : FirebaseImpl<UserSet>(
    Firebase.firestore.collection("sets"), ::toUserSet
)