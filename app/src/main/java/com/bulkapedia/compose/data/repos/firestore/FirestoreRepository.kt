package com.bulkapedia.compose.data.repos.firestore

import com.bulkapedia.compose.data.Entity
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration

open class FirestoreRepository<T : Entity>(
    private val collection: CollectionReference,
    private val transform: (DocumentSnapshot) -> T?
) : Repository<T> {

    override fun fetchAll(onSuccess: (List<T>) -> Unit): ListenerRegistration {
        return addListener(onSuccess)
    }

    override fun create(t: T): Task<DocumentReference> {
        return collection.add(t.toData())
    }

    override fun update(t: T): Task<Void> {
        return collection.document(t.id).update(t.toData())
    }

    override fun delete(t: T): Task<Void> {
        println("delete -> ${t.id}")
        return collection.document(t.id).delete()
    }

    private fun addListener(onSuccess: (List<T>) -> Unit): ListenerRegistration {
        return collection.addSnapshotListener { value, _ ->
            value?.documents?.mapNotNull(transform)?.apply(onSuccess)
        }
    }

}