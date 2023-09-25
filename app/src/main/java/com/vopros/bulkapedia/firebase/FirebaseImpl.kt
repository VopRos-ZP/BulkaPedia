package com.vopros.bulkapedia.firebase

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.core.Entity
import kotlinx.coroutines.job
import kotlinx.coroutines.tasks.asDeferred
import kotlinx.coroutines.tasks.await

open class FirebaseImpl<T: Entity>(
    private val ref: CollectionReference,
    private val transform: (DocumentSnapshot) -> T?
) : Firebase<T> {

    override fun listenAll(callback: Callback<List<T>>): ListenerRegistration {
        return ref.addSnapshotListener { value, error ->
            error?.localizedMessage?.let(callback.onError)
            value?.documents?.mapNotNull(transform)?.let(callback.onSuccess)
        }
    }

    override fun listenOne(id: String, callback: Callback<T>): ListenerRegistration {
        return listenAll(Callback(callback.onError) {
            when (val res = it.find { o -> o.id == id }) {
                null -> callback.onError("Server error: object with id $id not found")
                else -> callback.onSuccess(res)
            }
        })
    }

    override suspend fun fetchAll(): List<T> = ref.get().await().documents.mapNotNull(transform)

    override suspend fun fetchOne(id: String): T {
        return with(fetchAll()) {
            when (val res = find { it.id == id }) {
                null -> throw RuntimeException("Server error: object with id $id not found")
                else -> res
            }
        }
    }

    override suspend fun update(t: T) {
        val toMap = { it: T -> with(ObjectMapper()) { convertValue(it, object : TypeReference<Map<String, Any>>() {}) } }
        ref.document(t.id).set(toMap(t)).await()
    }

    override suspend fun delete(t: T) {
        ref.document(t.id).delete().await()
    }

}