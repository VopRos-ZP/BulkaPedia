package com.bulkapedia.data

import com.google.firebase.firestore.ListenerRegistration

interface Repository<T> {
    fun fetchAll(callback: CallBack<List<T>>): ListenerRegistration
    fun fetchBy(predicate: (T) -> Boolean, callBack: CallBack<T>): ListenerRegistration
    suspend fun create(t: T, id: String? = null)
    suspend fun update(t: T)
    suspend fun delete(t: T)
}