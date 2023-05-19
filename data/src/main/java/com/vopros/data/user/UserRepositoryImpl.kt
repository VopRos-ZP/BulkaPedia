package com.vopros.data.user

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.vopros.data.Database
import com.vopros.data.nowYearFormat
import com.vopros.domain.complete.Complete
import com.vopros.domain.user.User
import com.vopros.domain.user.UserRepository

class UserRepositoryImpl : UserRepository {

    override fun fetchAll(complete: Complete<List<User>>) {
        addListener({ snapshot ->
            val users = snapshot.children.mapNotNull { it.toUser() }
            complete.success.invoke(users)
        }) { complete.error.invoke(it) }
    }

    override fun fetchByLogin(login: String, complete: Complete<User?>) {
        findUserBy({ u -> u.email == login }, complete)
    }

    override fun fetchByLoginAndPassword(login: String, password: String, complete: Complete<User?>) {
        Database.auth.signInWithEmailAndPassword(login, password).addOnCompleteListener { res ->
            if (res.isSuccessful) {
                val uid = res.result.user!!.uid
                findUserBy({ u -> u.userId == uid }, complete)
            } else {
                res.exception?.message?.let { complete.error.invoke(it) }
            }
        }
    }

    override fun create(user: User, complete: Complete<User>) {
        Database.users.child(user.userId).setValue(user.toData()).addOnCompleteListener {
            if (it.isSuccessful) {
                complete.success.invoke(user)
            } else {
                it.exception?.message?.let { it1 -> complete.error.invoke(it1) }
            }
        }
    }

    override fun update(userId: String, user: User, complete: Complete<User>) {
        create(user, complete)
    }

    override fun register(user: User, complete: Complete<User>) {
        Database.auth.createUserWithEmailAndPassword(user.email, user.password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = task.result.user!!.uid
                user.userId = uid
                create(user, complete)
            } else {
                task.exception?.message?.let(complete.error::invoke)
            }
        }
    }

    override fun logout() {
        Database.auth.signOut()
    }

    override fun updateEmail(login: String, newEmail: String, complete: Complete<String>) {
        updateString(login, {
            it.updateEmail = nowYearFormat()
            it.email = newEmail
            it
        }, complete)
    }

    override fun updateNickname(login: String, newNickname: String, complete: Complete<String>) {
        updateString(login, {
            it.updateNickname = nowYearFormat()
            it.nickname = newNickname
            it
        }, complete)
    }

    private fun updateString(id: String, updateFun: (User) -> User, complete: Complete<String>) {
        fetchByLogin(id, Complete({ user ->
            user?.let { old ->
                updateFun.invoke(old).let {
                    update(it.userId, it, Complete({ updatedUser ->
                        complete.success.invoke(updatedUser.nickname)
                    }, complete.error::invoke))
                }
            }
        }, complete.error::invoke))
    }

    private fun findUserBy(predicate: (User) -> Boolean, complete: Complete<User?>) {
        addListener({ value ->
            val user = value.children.mapNotNull { d -> d.toUser() }.find(predicate)
            complete.success.invoke(user)
        }) { complete.error.invoke(it) }
    }

    private fun addListener(
        onSuccess: (DataSnapshot) -> Unit,
        onException: (String) -> Unit
    ) {
        Database.users.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                onSuccess.invoke(snapshot)
            }

            override fun onCancelled(error: DatabaseError) {
                onException.invoke(error.message)
            }
        })
    }

}