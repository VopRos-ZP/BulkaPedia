@file:Suppress("UNCHECKED_CAST")

package com.bulkapedia.database

import com.bulkapedia.labels.Stats
import com.bulkapedia.models.ChatModel
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

    fun getChatNode(): CollectionReference = fs.collection("chat")

    fun getAllUsers(func: (MutableList<User>) -> Unit) {
        getUsersNode().get().addOnSuccessListener { snapshot ->
            val users = mutableListOf<User>()
            for (sd in snapshot.children) {
                val strings = mutableMapOf<String, String>()
                val mains = mutableMapOf<String, Stats>()
                sd.children.forEach { data ->
                    if (data.key!! == "mains"){
                        data.children.forEach { main ->
                            val statsMap = mutableMapOf<String, Double>()
                            main.children.forEach { mainData ->
                                statsMap += mainData.key!! to mainData.value!!.toString().toDouble()
                            }
                            mains += main.key!! to Stats(statsMap["kills"]!!.toInt(),
                                statsMap["winrate"]!!,
                                statsMap["revives"]!!.toInt()
                            )
                        }
                    } else
                        strings += data.key!! to (data.value as String)
                }
                val user = User(strings["email"], strings["password"], strings["nickname"], mains)
                users.add(user)
            }
            func.invoke(users)
        }
    }

    fun retrieveUserByNickname(nickname: String, func: (User) -> Unit) {
        retrieveUser({ it.nickname == nickname }, func)
    }

    fun retrieveUserByEmail(email: String, func: (User) -> Unit) {
        retrieveUser({ it.email == email }, func)
    }

    fun containsEmail(email: String, func: (Boolean) -> Unit) {
        workConditionWithUser({ it.email == email }, func)
    }

    private fun retrieveUser(condition: (User) -> Boolean, func: (User) -> Unit) {
        getUsersNode().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (sd in snapshot.children) {
                    val strings = mutableMapOf<String, String>()
                    val mains = mutableMapOf<String, Stats>()
                    sd.children.forEach { data ->
                        if (data.key!! == "mains") {
                            data.children.forEach { main ->
                                val statsMap = mutableMapOf<String, Double>()
                                main.children.forEach { mainData ->
                                    statsMap += mainData.key!! to mainData.value!!.toString().toDouble()
                                }
                                mains += main.key!! to Stats(
                                    statsMap["kills"]!!.toInt(),
                                    statsMap["winrate"]!!,
                                    statsMap["revives"]!!.toInt()
                                )
                            }
                        } else
                            strings += data.key!! to (data.value as String)
                    }
                    val user = User(strings["email"], strings["password"], strings["nickname"], mains)
                    if (condition.invoke(user)) {
                        func.invoke(user)
                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    private fun workConditionWithUser(condition: (User) -> Boolean, func: (Boolean) -> Unit) {
        getUsersNode().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (sd in snapshot.children) {
                    val strings = mutableMapOf<String, String>()
                    val mains = mutableMapOf<String, Stats>()
                    sd.children.forEach { data ->
                        if (data.key!! == "mains"){
                            data.children.forEach { main ->
                                val statsMap = mutableMapOf<String, Double>()
                                main.children.forEach { mainData ->
                                    statsMap += mainData.key!! to mainData.value!!.toString().toDouble()
                                }
                                if (!main.key.isNullOrEmpty() && !statsMap.containsKey("kills")) {
                                    mains += main.key!! to Stats(
                                        statsMap["kills"]!!.toInt(),
                                        statsMap["winrate"]!!,
                                        statsMap["revives"]!!.toInt()
                                    )
                                }
                            }
                        } else
                            strings += data.key!! to (data.value as String)
                    }
                    val user = User(strings["email"], strings["password"],
                        strings["nickname"], mains)
                    if (condition.invoke(user)) {
                        func.invoke(true)
                        return
                    }
                }
                func.invoke(false)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun addMainsToUser(user: User) {
        getUsersNode().addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (sd in snapshot.children) {
                    for (data in sd.children) {
                        if (data.key!! == "email" && (data.value as String) == user.email) {
                            getUsersNode().child(sd.key!!).setValue(user)
                            break
                        }
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

    fun getSets(code: (MutableList<UserSet>) -> Unit) {
        getSetsNode().get().addOnSuccessListener { query ->
            val sets = mutableListOf<UserSet>()
            query.documents.forEach { doc ->
                sets.add(getUserSetBySnapshot(doc.id, doc))
            }
            code.invoke(sets)
        }
    }

    fun getFilterSets(predicate: (UserSet) -> Boolean, func: (MutableList<UserSet>) -> Unit) {
        getSetsNode().get().addOnSuccessListener { q ->
            val filtered = mutableListOf<UserSet>()
            val docs = mutableListOf<DocumentSnapshot>()
            for (doc in q.documents) {
                val set = getUserSetBySnapshot(doc.id, doc)
                if (set.hero == "0") continue
                if (predicate.invoke(set)) {
                    filtered.add(set)
                    docs.add(doc)
                }
            }
            docs.forEach { doc ->
                doc.reference.addSnapshotListener { v, _ ->
                    if (v != null) {
                        val set = getUserSetBySnapshot(v.id, v)
                        if (set.hero == "0") return@addSnapshotListener
                        val index = filtered.map { it.setId }.indexOf(set.setId)
                        if (index >= 0) {
                            filtered[index] = set
                            func.invoke(filtered)
                        }
                    }
                }
            }
        }
    }

    fun getSet(setId: String, func: (UserSet) -> Unit) {
        getSetsNode().document(setId).addSnapshotListener { value, _ ->
            if (value != null) {
                val set = getUserSetBySnapshot(setId, value)
                if (set.hero != "0")
                    func.invoke(set)
            }
        }
    }

    fun getChatInfoBySnapshot(value: DocumentSnapshot): ChatModel {
        val model = ChatModel("", "", "", "", "", false)
        try {
            model.author = getStringValue(value, "author")
            model.receiver = getStringValue(value, "receiver")
            model.date = getStringValue(value, "date")
            model.text = getStringValue(value, "text")
            model.image = getStringValue(value, "image")
            model.read = value.getBoolean("read") == true
        } catch (_: Exception) {}
        return model
    }

    private fun getUserSetBySnapshot(setId: String, value: DocumentSnapshot): UserSet {
        try {
            val gears = mapOf(
                GearCell.HEAD to getStringValue(value,"head"),
                GearCell.BODY to getStringValue(value,"body"),
                GearCell.ARM to getStringValue(value,"arm"),
                GearCell.LEG to getStringValue(value,"leg"),
                GearCell.DECOR to getStringValue(value,"decor"),
                GearCell.DEVICE to getStringValue(value,"device"),
            )
            val arr = value.get("user_like_ids") as MutableList<String>
            return UserSet(setId,
                getStringValue(value, "author"),
                getStringValue(value,"hero"), gears,
                getIntValue(value,"likes"), arr
            )
        } catch (_: java.lang.NullPointerException) {
            return UserSet(setId, "",
                "0", emptyMap(),
                0, mutableListOf()
            )
        }
    }

    private fun getStringValue(value: DocumentSnapshot, field: String): String {
        return try {
            value[field] as String
        } catch (_: NullPointerException) {
            ""
        }
    }

    private fun getIntValue(value: DocumentSnapshot, field: String): Int {
        return try {
            longToInt(value.getLong(field)!!)
        } catch (_: NullPointerException) {
            0
        }
    }

    private fun longToInt(l: Long): Int {
        return if (l <= Int.MAX_VALUE) Integer.parseInt(l.toString())
        else 0
    }
}
