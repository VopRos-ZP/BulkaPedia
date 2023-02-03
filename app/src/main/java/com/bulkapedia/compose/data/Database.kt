package com.bulkapedia.compose.data

import com.bulkapedia.compose.data.Comment.Companion.toComment
import com.bulkapedia.compose.data.Message.Companion.toMessage
import com.bulkapedia.compose.data.gears.Gear
import com.bulkapedia.compose.data.gears.Gear.Companion.toGear
import com.bulkapedia.compose.data.heroes.Hero
import com.bulkapedia.compose.data.heroes.Hero.Companion.toHero
import com.bulkapedia.compose.data.sets.GearCell
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.data.sets.UserSet.Companion.toUserSet
import com.bulkapedia.compose.data.User.Companion.toUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Database {

    /** Listeners **/
    fun addSetsSnapshotListener(listener: (List<UserSet>) -> Unit): ListenerRegistration {
        return Firebase.firestore.collection("sets").addSnapshotListener { value, _ ->
            val sets = value?.documents?.mapNotNull { it.toUserSet() } ?: emptyList()
            listener.invoke(sets)
        }
    }

    fun addCommentsSnapshotListener(listener: (List<Comment>) -> Unit): ListenerRegistration {
        return Firebase.firestore.collection("comments").addSnapshotListener { value, _ ->
            val comments = value?.documents?.mapNotNull { it.toComment() } ?: emptyList()
            listener.invoke(comments)
        }
    }

    fun addUsersSnapshotListener(listener: (List<User>) -> Unit): ValueEventListener {
        return Firebase.database.reference.child("users").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = snapshot.children.mapNotNull { it.toUser() }
                listener.invoke(users)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    fun addHeroesSnapshotListener(listener: (List<Hero>) -> Unit): ListenerRegistration {
        return Firebase.firestore.collection("heroes").addSnapshotListener { value, _ ->
            val heroes = value?.documents?.mapNotNull { it.toHero() } ?: emptyList()
            listener.invoke(heroes)
        }
    }

    fun addGearsSnapshotListener(listener: (List<Gear>) -> Unit): ListenerRegistration {
        return Firebase.firestore.collection("gears").addSnapshotListener { value, _ ->
            val gears = value?.documents?.mapNotNull { it.toGear() } ?: emptyList()
            listener.invoke(gears)
        }
    }

    fun addMessagesSnapshotListener(listener: (List<Message>) -> Unit): ListenerRegistration {
        return Firebase.firestore.collection("chat").addSnapshotListener { value, _ ->
            val messages = value?.documents?.mapNotNull { it.toMessage() } ?: emptyList()
            listener.invoke(messages)
        }
    }

    fun addMechanicsSnapshotListener(): ListenerRegistration {
        return Firebase.firestore.collection("mechanics").addSnapshotListener { value, _ ->
            // listen mechanics
        }
    }

    /** Addition functions **/
    fun addComment(comment: Comment) {
        Firebase.firestore.collection("comments").add(comment)
    }

    fun addSet(set: UserSet) {
        val data = mapOf(
            "author" to set.from,
            "hero" to set.hero,
            "head" to set.gears[GearCell.HEAD],
            "body" to set.gears[GearCell.BODY],
            "arm" to set.gears[GearCell.ARM],
            "leg" to set.gears[GearCell.LEG],
            "decor" to set.gears[GearCell.DECOR],
            "device" to set.gears[GearCell.DEVICE],
            "likes" to set.likes,
            "user_like_ids" to set.userLikeIds,
        )
        Firebase.firestore.collection("sets").add(data)
    }

    fun addMessage(message: Message) {
        Firebase.firestore.collection("chat").add(message)
    }

    /** Removing functions **/
    fun removeComment(comment: Comment) {
        Firebase.firestore.collection("comments")
            .whereEqualTo("from", comment.from)
            .whereEqualTo("date", comment.date)
            .whereEqualTo("set", comment.set)
            .get().addOnSuccessListener {
                it.documents.forEach { doc -> doc.reference.delete() }
            }
    }

    fun removeSet(set: UserSet) {
        Firebase.firestore.collection("sets").document(set.userSetId).delete().addOnSuccessListener {
            Firebase.firestore.collection("comments").whereEqualTo("set", set.userSetId).get().addOnSuccessListener { cq ->
                cq.documents.forEach { it.reference.delete() }
            }
        }
    }

    fun removeUser(user: User, onSuccess: () -> Unit = {}) {
        // Удалить пользовательский аккаунт
        findUser(user) { id, nu ->
            Firebase.database.reference.child("users").child(id).removeValue().addOnSuccessListener {
                Firebase.firestore.collection("sets").whereEqualTo("author", nu.nickname).get().addOnSuccessListener { sq ->
                    sq.documents.mapNotNull { it.toUserSet() }
                        .forEach { set -> removeSet(set) }
                }
                onSuccess.invoke()
            }
        }
    }

    fun findUser(user: User, onError: (String) -> Unit = {}, onFind: (String, User) -> Unit) {
        findUserBy({ it.email == user.email && it.nickname == user.nickname }, onError, onFind)
    }

    fun findUserByNickname(nickname: String, onError: (String) -> Unit = {}, onFind: (String, User) -> Unit) {
        findUserBy({ it.nickname == nickname }, onError, onFind)
    }

    fun findUserByEmail(email: String, onError: (String) -> Unit = {}, onFind: (String, User) -> Unit) {
        findUserBy({ it.email == email }, onError, onFind)
    }

    private fun findUserBy(predicate: (User) -> Boolean, onError: (String) -> Unit = {}, onFind: (String, User) -> Unit) {
        Firebase.database.reference.child("users").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    val fu = child.toUser()
                    if (fu != null) {
                        if (predicate.invoke(fu)) {
                            onFind.invoke(child.key!!, fu)
                            return
                        }
                    }
                }
                onError.invoke("User not found")
            }

            override fun onCancelled(error: DatabaseError) { onError.invoke(error.message) }
        })
    }

    /** Authorization **/
    fun signIn(email: String, password: String, onSuccess: (User) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            findUserByEmail(email) { _, user ->
                onSuccess.invoke(user)
            }
        }
    }

    fun signUp(email: String, password: String, nickname: String, onSuccess: (User) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            val user = User(email, password, nickname)
            Firebase.database.reference.child("users").child(it.user!!.uid).setValue(user)
            onSuccess.invoke(user)
        }
    }

    fun sendPasswordResetEmail(email: String, onSuccess: () -> Unit) {
        Firebase.auth.sendPasswordResetEmail(email).addOnSuccessListener {
            onSuccess.invoke()
        }
    }

}