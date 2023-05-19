package com.vopros.domain.user

import com.vopros.domain.complete.Complete
import com.vopros.domain.complete.VoidComplete

interface UserRepository {
    fun fetchAll(complete: Complete<List<User>>)
    fun fetchByLoginAndPassword(login: String, password: String, complete: Complete<User?>)
    fun fetchByLogin(login: String, complete: Complete<User?>)
    fun create(user: User, complete: Complete<User>)
    fun update(userId: String, user: User, complete: Complete<User>)
    fun register(user: User, complete: Complete<User>)
    fun logout()
    // new functions
    fun updateEmail(login: String, newEmail: String, complete: Complete<String>)
    fun updateNickname(login: String, newNickname: String, complete: Complete<String>)
}