package vopros.bulkapedia.firebase

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import vopros.bulkapedia.core.Callback
import vopros.bulkapedia.core.Entity
import kotlinx.coroutines.tasks.await

open class FirebaseImpl<T: Entity>(
    private val ref: CollectionReference,
    private val transform: (DocumentSnapshot) -> T?
) : Firebase<T> {

    override fun listenAll(callback: Callback<List<T>>): ListenerRegistration {
        return ref.addSnapshotListener { value, error ->
            value?.documents?.mapNotNull(transform)?.let(callback.onSuccess)
            error?.localizedMessage?.let(callback.onError)
        }
    }

    override fun listenOne(id: String, callback: Callback<T>): ListenerRegistration {
        return ref.addSnapshotListener { value, error ->
            error?.localizedMessage?.let(callback.onError)
            val docs = value?.documents?.mapNotNull(transform)
            when (val res = docs?.find { o -> o.id == id }) {
                null -> callback.onError("Server error: object with id $id not found")
                else -> callback.onSuccess(res)
            }
        }
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
        Log.d("Update", "update: ${t.id}")
        ref.document(t.id).set(t.toData()).await()
    }

    override suspend fun delete(t: T) {
        ref.document(t.id).delete().await()
    }

}