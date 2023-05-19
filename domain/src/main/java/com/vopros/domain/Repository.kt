package com.vopros.domain

import com.google.firebase.firestore.ListenerRegistration
import com.vopros.domain.complete.Complete
import com.vopros.domain.complete.VoidComplete

interface Repository<T> {
    fun fetchAll(callback: Complete<List<T>>): ListenerRegistration
    fun fetchById(id: String, callback: Complete<T>): ListenerRegistration
    fun create(t: T, callback: Complete<T>)
    fun update(t: T, callback: Complete<T>)
    fun delete(t: T, callback: VoidComplete)
}