package com.bulkapedia.database

import com.bulkapedia.sets.GearCell
import com.bulkapedia.sets.UserSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Database {

    private val fs = Firebase.firestore
    private val db = Firebase.database

    fun getUsersNode(): DatabaseReference = db.getReference("users")

    fun getSetsNode(): CollectionReference = fs.collection("sets")

    fun getCommentsNode(): CollectionReference = fs.collection("comments")

    fun retrieveUserByNickname(nickname: String, func: (User) -> Unit) {
        getUsersNode().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (sd in snapshot.children) {
                    val strings = mutableMapOf<String, String>()
                    sd.children.forEach { data ->
                        strings += data.key!! to (data.value as String)
                    }
                    val user = User(strings["login"], strings["password"], strings["nickname"])
                    if (user.nickname == nickname) {
                        func.invoke(user)
                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun retrieveUserByEmail(email: String, func: (User) -> Unit) {
        getUsersNode().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (sd in snapshot.children) {
                    val strings = mutableMapOf<String, String>()
                    sd.children.forEach { data ->
                        strings += data.key!! to (data.value as String)
                    }
                    val user = User(strings["email"], strings["password"], strings["nickname"])
                    if (user.email == email) {
                        func.invoke(user)
                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    /** User sets **/
    fun addUserSet(set: UserSet) {
        val userSet = mapOf(
            "author" to set.from,
            "hero" to set.hero,
            "head" to set.gears[GearCell.HEAD], "body" to set.gears[GearCell.BODY],
            "arm" to set.gears[GearCell.ARM], "leg" to set.gears[GearCell.LEG],
            "decor" to set.gears[GearCell.DECOR], "device" to set.gears[GearCell.DEVICE],
            "likes" to set.likes,
            "user_like_ids" to set.userLikeIds
        )
        getSetsNode().document(set.setId).set(userSet)
    }

    fun getSets(code: (MutableList<UserSet>) -> Unit): MutableList<UserSet> {
        val sets = mutableListOf<UserSet>()
        getSetsNode().get().addOnSuccessListener { query ->
            query.documents.forEach { doc ->
                val gears = mapOf(
                    GearCell.HEAD to doc.getLong("head")!!.toInt(),
                    GearCell.BODY to doc.getLong("body")!!.toInt(),
                    GearCell.ARM to doc.getLong("arm")!!.toInt(),
                    GearCell.LEG to doc.getLong("leg")!!.toInt(),
                    GearCell.DECOR to doc.getLong("decor")!!.toInt(),
                    GearCell.DEVICE to doc.getLong("device")!!.toInt()
                )
                sets.add(UserSet(
                    doc.id,
                    doc.getString("author")!!,
                    doc.getLong("hero")!!.toInt(),
                    gears, doc.getLong("likes")!!.toInt(),
                    doc.get("user_like_ids") as MutableList<String>
                ))
            }
            code.invoke(sets)
        }
        return sets
    }

    /** Comments **/
    // nothing

}
