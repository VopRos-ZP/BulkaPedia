package bulkapedia.instances

import bulkapedia.Callback
import bulkapedia.StoreRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await

open class RepositoryImpl<T>(
    private val ref: CollectionReference,
    private val transform: (DocumentSnapshot) -> T?,
    private val id: (T) -> String,
    private val toDTO: (T) -> Any
): StoreRepository<T> {

    override fun listenAll(callback: Callback<List<T>>): ListenerRegistration {
        return ref.addSnapshotListener { value, error ->
            error?.localizedMessage?.apply { callback.onError?.invoke(this) }
            value?.documents?.mapNotNull(transform)?.apply(callback.onSuccess)
        }
    }

    override suspend fun fetchAll(): List<T> {
        return ref.get().await().documents.mapNotNull(transform)
    }

    override suspend fun fetchById(id: String): T? {
        return fetchAll().find { id(it) == id }
    }

    override suspend fun create(t: T) {
        ref.document().set(toDTO(t)).await()
    }

    override suspend fun update(t: T) {
        ref.document(id(t)).set(toDTO(t)).await()
    }

    override suspend fun delete(t: T) {
        ref.document(id(t)).delete().await()
    }
}