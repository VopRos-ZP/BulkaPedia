package com.bulkapedia.compose.data.repos.firestore

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreDB {

    companion object {
        val heroes = getCollection("heroes")
        val maps = getCollection("maps")
        val sets = getCollection("sets")
        val comments = getCollection("comments")
        val categories = getCollection("categories")
        val messages = getCollection("messages") // chat
        val gears = getCollection("gears")
        val heroInfo = getCollection("heroInfo")
        val mechanics = getCollection("mechanics")

        private fun getCollection(path: String) = Firebase.firestore.collection(path)
    }

}