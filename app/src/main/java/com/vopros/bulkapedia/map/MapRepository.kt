package com.vopros.bulkapedia.map

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vopros.bulkapedia.firebase.FirebaseImpl
import com.vopros.bulkapedia.firebase.toGameMap
import javax.inject.Inject

class MapRepository @Inject constructor() : FirebaseImpl<GameMap>(
    Firebase.firestore.collection("maps"), ::toGameMap
)