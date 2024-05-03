package ru.bulkapedia.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import ru.bulkapedia.domain.model.User
import ru.bulkapedia.domain.repository.UserRepository
import ru.bulkapedia.domain.utils.Table
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {

    private val table = Table.USERS
    private val collection = Firebase.firestore.collection(table)

    override suspend fun fetchAll(): List<User> {
        return collection.get().await().toObjects()
    }

    override suspend fun fetchById(id: String): User {
        return collection.document(id).get().await().toObject()!!
    }

    override suspend fun upsert(user: User) {

    }

    override suspend fun delete(id: String) {
        collection.document(id).delete().await()
    }

    override suspend fun listenAll(): Flow<List<User>> {
        return collection.snapshots().map { it.toObjects() }
    }

    override suspend fun listenById(id: String): Flow<User> {
        return callbackFlow {
            awaitClose {  }
        }
    }

    override suspend fun subscribe() {

    }

    override suspend fun dispose() {

    }
}