package vopros.bulkapedia.userSet

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import vopros.bulkapedia.firebase.FirebaseImpl
import vopros.bulkapedia.firebase.toUserSet
import javax.inject.Inject

class SetRepository @Inject constructor() : FirebaseImpl<UserSet>(
    Firebase.firestore.collection("sets"), ::toUserSet
)