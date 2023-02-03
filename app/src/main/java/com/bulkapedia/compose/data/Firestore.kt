package com.bulkapedia.compose.data

import com.bulkapedia.compose.data.Comment.Companion.toComment
import com.bulkapedia.compose.data.heroes.Hero
import com.bulkapedia.compose.data.heroes.Hero.Companion.toHero
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import com.bulkapedia.compose.data.Map.Companion.toMap
import com.bulkapedia.compose.data.Message.Companion.toMessage
import com.bulkapedia.compose.data.gears.Gear
import com.bulkapedia.compose.data.gears.Gear.Companion.toGear
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.data.sets.UserSet.Companion.toUserSet
import com.bulkapedia.compose.data.User.Companion.toUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database

object Firestore {

    private fun fs(): FirebaseFirestore = Firebase.firestore

    object Heroes {
        private val ref = fs().collection("heroes")

        suspend fun getHeroes(): List<Hero>? {
            return try {
                ref.get().await().documents.mapNotNull { it.toHero() }
            } catch (_: Exception) { null }
        }

    }

    object Maps {
        private val ref = fs().collection("maps")

        suspend fun getMaps(): List<Map>? {
            return try {
                ref.get().await().documents.mapNotNull { it.toMap() }
            } catch (_: Exception) { null }
        }
    }

    object Comments {
        private val ref = fs().collection("comments")

        suspend fun getComments(): List<Comment>? {
            return try {
                ref.get().await().documents.mapNotNull { it.toComment() }
            } catch (_: Exception) { null }
        }
    }

    object Sets {
        private val ref = fs().collection("sets")

        suspend fun getSets(): List<UserSet>? {
            return try {
                ref.get().await().documents.mapNotNull { it.toUserSet() }
            } catch (_: Exception) { null }
        }
    }

    object Gears {
        private val ref = fs().collection("gears")

        suspend fun getGears(): List<Gear>? {
            return try {
                ref.get().await().documents.mapNotNull { it.toGear() }
            } catch (_: Exception) { null }
        }
    }

    object Messages {
        private val ref = fs().collection("chat")

        suspend fun getMessages(): List<Message>? {
            return try {
                ref.get().await().documents.mapNotNull { it.toMessage() }
            } catch (_: Exception) { null }
        }
    }

}

object FirebaseDB {

    private fun db(): DatabaseReference = Firebase.database.reference

    object Users {
        private val ref = db().child("users")

        suspend fun getUsers(): List<User>? {
            return try {
                ref.get().await().children.mapNotNull { it.toUser() }
            } catch (_: Exception) { null }
        }

    }

}