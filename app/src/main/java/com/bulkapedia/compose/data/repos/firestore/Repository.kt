package com.bulkapedia.compose.data.repos.firestore

import com.bulkapedia.compose.data.Entity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ListenerRegistration

interface Repository<T : Entity> {
    fun fetchAll(onSuccess: (List<T>) -> Unit): ListenerRegistration
    fun create(t: T): Task<DocumentReference>
    fun update(t: T): Task<Void>
    fun delete(t: T): Task<Void>
}