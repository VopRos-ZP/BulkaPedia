package com.vopros.bulkapedia.hero

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vopros.bulkapedia.firebase.FirebaseImpl
import com.vopros.bulkapedia.firebase.toHero
import javax.inject.Inject

class HeroRepository @Inject constructor() : FirebaseImpl<Hero>(
    Firebase.firestore.collection("heroes"), ::toHero
)