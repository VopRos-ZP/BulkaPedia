package vopros.bulkapedia.user

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import vopros.bulkapedia.firebase.FirebaseImpl
import vopros.bulkapedia.firebase.toUser
import javax.inject.Inject

class UserRepository @Inject constructor(): FirebaseImpl<User>(
    Firebase.firestore.collection("users"), ::toUser
)