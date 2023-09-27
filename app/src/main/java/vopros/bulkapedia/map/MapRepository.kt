package vopros.bulkapedia.map

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import vopros.bulkapedia.firebase.FirebaseImpl
import vopros.bulkapedia.firebase.toGameMap
import javax.inject.Inject

class MapRepository @Inject constructor() : FirebaseImpl<GameMap>(
    Firebase.firestore.collection("maps"), ::toGameMap
)