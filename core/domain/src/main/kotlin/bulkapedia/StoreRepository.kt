package bulkapedia

import com.google.firebase.firestore.ListenerRegistration

interface StoreRepository<T> {
    fun listenAll(callback: Callback<List<T>>): ListenerRegistration
    suspend fun fetchAll(): List<T>
    suspend fun fetchById(id: String): T?
    suspend fun create(t: T)
    suspend fun update(t: T)
    suspend fun delete(t: T)
}