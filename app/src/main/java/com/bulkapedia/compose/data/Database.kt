package com.bulkapedia.compose.data

import com.bulkapedia.compose.data.Comment.Companion.toComment
import com.bulkapedia.compose.data.Map.Companion.toMap
import com.bulkapedia.compose.data.Message.Companion.toMessage
import com.bulkapedia.compose.data.gears.Gear
import com.bulkapedia.compose.data.gears.Gear.Companion.toGear
import com.bulkapedia.compose.data.heroes.Hero
import com.bulkapedia.compose.data.heroes.Hero.Companion.toHero
import com.bulkapedia.compose.data.sets.GearCell
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.data.sets.UserSet.Companion.toUserSet
import com.bulkapedia.compose.data.User.Companion.toUser
import com.bulkapedia.compose.data.category.Category
import com.bulkapedia.compose.data.category.Category.Companion.toCategory
import com.bulkapedia.compose.data.category.HeroInfo
import com.bulkapedia.compose.data.category.HeroInfo.Companion.toHeroInfo
import com.bulkapedia.compose.data.category.Mechanic
import com.bulkapedia.compose.data.category.Mechanic.Companion.toMechanic
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.collections.Map

class Database {

    /** Listeners **/
    fun addMapsSnapshotListener(listener: (List<com.bulkapedia.compose.data.Map>) -> Unit): ListenerRegistration {
        return addSnapshotListener(Firebase.firestore.collection("maps"), { it.toMap() }, listener)
    }

    fun addSetsSnapshotListener(listener: (List<UserSet>) -> Unit): ListenerRegistration {
        return addSnapshotListener(Firebase.firestore.collection("sets"), { it.toUserSet() }, listener)
    }

    fun addCommentsSnapshotListener(listener: (List<Comment>) -> Unit): ListenerRegistration {
        return addSnapshotListener(Firebase.firestore.collection("comments"), { it.toComment() }, listener)
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
        return addSnapshotListener(Firebase.firestore.collection("heroes"), { it.toHero() }, listener)
    }

    fun addGearsSnapshotListener(listener: (List<Gear>) -> Unit): ListenerRegistration {
        return addSnapshotListener(Firebase.firestore.collection("gears"), { it.toGear() }, listener)
    }

    fun addMessagesSnapshotListener(listener: (List<Message>) -> Unit): ListenerRegistration {
        return addSnapshotListener(Firebase.firestore.collection("chat"), { it.toMessage() }, listener)
    }

    fun addMechanicsSnapshotListener(listener: (List<Mechanic>) -> Unit): ListenerRegistration {
        return addSnapshotListener(Firebase.firestore.collection("mechanics"), { it.toMechanic() }, listener)
    }

    fun addCategoriesSnapshotListener(listener: (List<Category>) -> Unit): ListenerRegistration {
        return addSnapshotListener(Firebase.firestore.collection("categories"), { it.toCategory() }, listener)
    }

    fun addHeroInfoSnapshotListener(listener: (List<HeroInfo>) -> Unit): ListenerRegistration {
        return addSnapshotListener(Firebase.firestore.collection("heroInfo"), { it.toHeroInfo() }, listener)
    }

    private fun <T> addSnapshotListener(collection: CollectionReference, toType: (DocumentSnapshot) -> T?, listener: (List<T>) -> Unit): ListenerRegistration {
        return collection.addSnapshotListener { value, _ ->
            val docs = value?.documents?.mapNotNull { toType.invoke(it) } ?: emptyList()
            listener.invoke(docs)
        }
    }

    /** Addition functions **/
    fun addComment(comment: Comment) {
        Firebase.firestore.collection("comments").add(comment)
    }

    fun addSet(set: UserSet) {
        Firebase.firestore.collection("sets").add(setData(set))
    }

    fun addMessage(message: Message) {
        Firebase.firestore.collection("chat").add(message)
    }

    fun addHeroInfo(heroInfo: HeroInfo) {
        val data = mapOf(
            "hero" to heroInfo.hero,
            "description" to heroInfo.description,
            "video" to heroInfo.video,
        )
        Firebase.firestore.collection("heroInfo").document(heroInfo.id).set(data)
    }

    fun updateCategory(category: Category) {
        Firebase.firestore.collection("categories")
            .whereEqualTo("destination", category.destination)
            .get().addOnSuccessListener { q ->
                for (doc in q.documents) {
                    doc.reference.set(category)
                }
            }
    }

    fun updateSet(set: UserSet) {
        Firebase.firestore.collection("sets")
            .document(set.userSetId).update(setData(set))
    }

    fun updateNickname(email: String, nickname: String, onSuccess: (User) -> Unit) {
        findUserByEmail(email) { id, user ->
            user.nickname = nickname
            user.updateNickname = nowYearFormat()
            updateUser(id, user, onSuccess)
        }
    }

    fun updateEmail(email: String, newEmail: String, onSuccess: (User) -> Unit) {
        findUserByEmail(email) { id, user ->
            user.email = newEmail
            user.updateEmail = nowYearFormat()
            updateUser(id, user, onSuccess)
        }
    }

    fun updatePassword(user: User, onSuccess: (User) -> Unit) {
        findUserByEmail(user.email) { id, newUser ->
            updateUser(id, newUser, onSuccess)
        }
    }

    private fun updateUser(id: String, user: User, onSuccess: (User) -> Unit) {
        Firebase.database.reference.child("users").child(id).setValue(user).addOnSuccessListener {
            onSuccess.invoke(user)
        }
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
                onError.invoke("Пользователь не найден")
            }

            override fun onCancelled(error: DatabaseError) { onError.invoke(error.message) }
        })
    }

    /** Authorization **/
    fun signIn(email: String, password: String, onError: (String) -> Unit, onSuccess: (User) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                findUserByEmail(email) { _, user ->
                    onSuccess.invoke(user)
                }
            } else {
                onError.invoke(it.exception?.localizedMessage ?: "Ошибка входа")
            }
        }
    }

    fun signUp(email: String, password: String, nickname: String, onError: (String) -> Unit, onSuccess: (User) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnFailureListener { e -> onError.invoke(e.localizedMessage ?: "Ошибка регистрации") }
            .addOnSuccessListener {
                val now = nowYearFormat()
                val user = User(email, password, nickname, now, now)
                Firebase.database.reference.child("users").child(it.user!!.uid).setValue(user)
                onSuccess.invoke(user)
            }
    }

    fun logout() {
        Firebase.auth.signOut()
    }

    fun sendPasswordResetEmail(email: String, onSuccess: () -> Unit) {
        Firebase.auth.sendPasswordResetEmail(email).addOnSuccessListener {
            onSuccess.invoke()
        }
    }

    /** Help functions **/
    private fun setData(set: UserSet): Map<String, Any?> {
        return mapOf(
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
    }

}