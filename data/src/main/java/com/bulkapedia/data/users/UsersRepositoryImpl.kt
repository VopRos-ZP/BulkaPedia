package com.bulkapedia.data.users

import bulkapedia.users.User.Companion.toUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UsersRepositoryImpl : UsersRepository {

    private val ref = Firebase.database.reference.child("users")
    private val auth = Firebase.auth

    override fun fetchAll(onSuccess: (List<User>) -> Unit): ValueEventListener {
        return ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.mapNotNull { it.toUser() }.apply(onSuccess)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun update(user: User, onSuccess: (User) -> Unit) {
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ch in snapshot.children) {
                    val u = ch.toUser()
                    if (u?.password == user.password) {
                        ref.child(ch.key!!).setValue(user)
                        onSuccess(user)
                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    override fun findByEmail(email: String, onSuccess: (User?) -> Unit) {
        findUserBy({ it.email == email }, onSuccess)
    }

    override fun findByNickname(nickname: String, onSuccess: (User?) -> Unit) {
        findUserBy({ it.nickname == nickname }, onSuccess)
    }

    private fun create(uid: String, user: User, onSuccess: (User) -> Unit) {
        ref.child(uid).setValue(user).addOnSuccessListener { onSuccess(user) }
    }

    override fun register(user: User, onSuccess: (User) -> Unit) {
        auth.createUserWithEmailAndPassword(user.email, user.password).addOnSuccessListener {
            create(it.user!!.uid, user, onSuccess)
        }
    }

    override fun login(email: String, password: String, onSuccess: (User) -> Unit, onError: (String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            findByEmail(email) { it?.apply(onSuccess) }
        }.addOnFailureListener {
            onError(it.localizedMessage)
        }
    }

    override fun logout() {
        auth.signOut()
    }

    override fun remove(listener: ValueEventListener) {
        ref.removeEventListener(listener)
    }

    private fun findUserBy(predicate: (User) -> Boolean, onSuccess: (User?) -> Unit) {
        ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.mapNotNull { it.toUser() }.find(predicate).apply(onSuccess)
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

}