package com.bulkapedia.database

import com.bulkapedia.sets.GearCell
import com.bulkapedia.sets.UserSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
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

    fun containsEmail(email: String, func: (Boolean) -> Unit) {
        getUsersNode().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (sd in snapshot.children) {
                    val strings = mutableMapOf<String, String>()
                    sd.children.forEach { data ->
                        strings += data.key!! to (data.value as String)
                    }
                    val user = User(strings["email"], strings["password"], strings["nickname"])
                    if (user.email == email) {
                        func.invoke(true)
                        return
                    }
                }
                func.invoke(false)
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

    fun getFilterSets(predicate: (UserSet) -> Boolean, func: (MutableList<UserSet>) -> Unit) {
        getSetsNode().get().addOnSuccessListener { q ->
            val filtered = mutableListOf<UserSet>()
            val docs = mutableListOf<DocumentSnapshot>()
            for (doc in q.documents) {
                val set = getUserSetBySnapshot(doc.id, doc)
                if (set.hero == 0) continue
                if (predicate.invoke(set)) {
                    filtered.add(set)
                    docs.add(doc)
                }
            }
            docs.forEach { doc ->
                doc.reference.addSnapshotListener { v, _ ->
                    if (v != null) {
                        val set = getUserSetBySnapshot(v.id, v)
                        if (set.hero == 0) return@addSnapshotListener
                        val index = filtered.map { it.setId }.indexOf(set.setId)
                        if (index >= 0) {
                            filtered[index] = set
                            func.invoke(filtered)
                        }
                    }
                }
            }
        }
//        getSetsNode().addSnapshotListener { v, _ ->
//            val filtered = mutableListOf<UserSet>()
//            if (v != null) {
//                for (doc in v.documents) {
//                    val set = getUserSetBySnapshot(doc.id, doc)
//                    if (set.hero == 0) continue
//                    if (predicate.invoke(set))
//                        filtered.add(set)
//                }
//                func.invoke(filtered)
//            }
//        }
    }


    fun getFilter2Sets(predicate1: (UserSet) -> Boolean, predicate2: (UserSet) -> Boolean,
                       func: (MutableList<UserSet>, MutableList<UserSet>) -> Unit) {
//        getFilterSets(predicate1) { yourSets ->
//            getFilterSets(predicate2) { favSets ->
//                func.invoke(yourSets, favSets)
//            }
//        }
        getSetsNode().addSnapshotListener { v, _ ->
            val filtered1 = mutableListOf<UserSet>()
            val filtered2 = mutableListOf<UserSet>()
            if (v != null) {
                for (doc in v.documents) {
                    val set = getUserSetBySnapshot(doc.id, doc)
                    if (set.hero == 0) continue
                    if (predicate1.invoke(set))
                        filtered1.add(set)
                    else if (predicate2.invoke(set))
                        filtered2.add(set)
                }
                func.invoke(filtered1, filtered2)
            }
        }
    }

    fun getSet(setId: String, func: (UserSet) -> Unit) {
        getSetsNode().document(setId).addSnapshotListener { value, _ ->
            if (value != null) {
                val set = getUserSetBySnapshot(setId, value)
                if (set.hero != 0)
                    func.invoke(set)
            }
        }
    }

    private fun getUserSetBySnapshot(setId: String, value: DocumentSnapshot): UserSet {
        try {
            val gears = mapOf(
                GearCell.HEAD to longToInt(value.getLong("head")!!),
                GearCell.BODY to longToInt(value.getLong("body")!!),
                GearCell.ARM to longToInt(value.getLong("arm")!!),
                GearCell.LEG to longToInt(value.getLong("leg")!!),
                GearCell.DECOR to longToInt(value.getLong("decor")!!),
                GearCell.DEVICE to longToInt(value.getLong("device")!!),
            )
            val arr = value.get("user_like_ids") as MutableList<String>
            return UserSet(setId,
                value["author"] as String,
                longToInt(value.getLong("hero")!!), gears,
                longToInt(value.getLong("likes")!!), arr
            )
        } catch (_: java.lang.NullPointerException) {
            return UserSet(setId, "",
                0, emptyMap(),
                0, mutableListOf()
            )
        }
    }

    private fun longToInt(l: Long): Int {
        return if (l <= Int.MAX_VALUE) Integer.parseInt(l.toString())
        else 0
    }

    /** Comments **/
    // nothing

}
