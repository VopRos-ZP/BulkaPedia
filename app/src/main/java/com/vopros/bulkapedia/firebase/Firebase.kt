package com.vopros.bulkapedia.firebase

import com.google.firebase.firestore.ListenerRegistration
import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.core.Entity

interface Firebase<T: Entity> {
    fun listenAll(callback: Callback<List<T>>): ListenerRegistration
    fun listenOne(id: String, callback: Callback<T>): ListenerRegistration
    suspend fun fetchAll(): List<T>
    suspend fun fetchOne(id: String): T
    suspend fun update(t: T)
    suspend fun delete(t: T)
}