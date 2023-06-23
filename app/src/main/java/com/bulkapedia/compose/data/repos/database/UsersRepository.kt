package com.bulkapedia.compose.data.repos.database

import com.google.firebase.database.ValueEventListener

interface UsersRepository {
    fun fetchAll(onSuccess: (List<User>) -> Unit): ValueEventListener
    fun update(user: User, onSuccess: (User) -> Unit)
    fun findByEmail(email: String, onSuccess: (User?) -> Unit)
    fun findByNickname(nickname: String, onSuccess: (User?) -> Unit)
    fun register(user: User, onSuccess: (User) -> Unit)
    fun login(email: String, password: String, onSuccess: (User) -> Unit, onError: (String?) -> Unit)
    fun remove(listener: ValueEventListener)
    fun logout()
}