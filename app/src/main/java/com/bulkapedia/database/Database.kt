package com.bulkapedia.database

import com.bulkapedia.MAIN
import com.bulkapedia.sets.GearCell
import com.bulkapedia.sets.UserSet
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Database {

    private val db = Firebase.firestore

    fun getUsersNode(): CollectionReference = db.collection("users")

    fun getSetsNode(): CollectionReference = db.collection("sets")

    fun getCommentsNode(): CollectionReference = db.collection("comments")

    /** Users **/
    fun addUser(user: User) {
        getUsersNode().add(user)
    }

    fun getUserByLogin(login: String, code: (User?) -> Unit)
    : User? = getUser("login", login, code)

    fun getUserByNickname(nickname: String, code: (User?) -> Unit): User? = getUser("nickname", nickname, code)

    private fun getUser(field: String, search: String, code: (User?) -> Unit): User? {
        var user: User? = null
        getUsersNode().whereEqualTo(field, search).get().addOnSuccessListener { query ->
            user = query.documents[0].toObject(User::class.java)
            code.invoke(user)
            return@addOnSuccessListener
        }
        return user
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
