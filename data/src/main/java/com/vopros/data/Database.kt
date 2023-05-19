package com.vopros.data

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Database {

    companion object {
        private val fs = Firebase.firestore
        private val database = Firebase.database
        val auth = Firebase.auth

        val users = database.reference.child("users")
        val sets = fs.collection("sets")
        val maps = fs.collection("maps")
        val comments = fs.collection("comments")
        val heroes = fs.collection("heroes")
        val mechanics = fs.collection("mechanics")
        val heroInfo = fs.collection("heroInfo")
        val messages = fs.collection("chat")
        val categories = fs.collection("categories")
        val gears = fs.collection("gears")
    }

}