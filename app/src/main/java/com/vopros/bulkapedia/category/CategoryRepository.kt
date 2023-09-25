package com.vopros.bulkapedia.category

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.vopros.bulkapedia.firebase.FirebaseImpl
import com.vopros.bulkapedia.firebase.toCategory
import javax.inject.Inject

class CategoryRepository @Inject constructor() : FirebaseImpl<Category>(
    Firebase.firestore.collection("categories"), ::toCategory
)