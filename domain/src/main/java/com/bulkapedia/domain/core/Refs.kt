package com.bulkapedia.domain.core

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object Refs {

    object Store {

        private fun refCollection(name: String) = Firebase.firestore.collection(name)

        val categories = refCollection("categories")
        val comments = refCollection("comments")
        val gears = refCollection("gears")
        val heroInfo = refCollection("heroInfo")
        val heroes = refCollection("heroes")
        val mains = refCollection("mains")
        val maps = refCollection("maps")
        val mechanics = refCollection("mechanics")
        val messages = refCollection("chat")
        val sets = refCollection("sets")
    }

}