package com.bulkapedia.domain.core

import bulkapedia.Callback
import com.bulkapedia.data.Repository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.tasks.await

open class RepositoryImpl<T>(
    private val collection: CollectionReference,
    private val transform: (DocumentSnapshot) -> T?,
    private val getId: (T) -> String,
    private val toData: (T) -> Map<String, Any>
) : Repository<T> {

    override fun fetchAll(callback: CallBack<List<T>>): ListenerRegistration {
        return addListener(callback)
    }

    override fun fetchBy(predicate: (T) -> Boolean, callBack: CallBack<T>): ListenerRegistration {
        return addListener(CallBack({ list ->
            when (val t = list.find(predicate)) {
                null -> callBack.onError("Данные не найдены")
                else -> callBack.onSuccess(t)
            }
        }, callBack.onError))
    }

    override suspend fun create(t: T, id: String?) {
        when (id != null) {
            true -> collection.document(id)
            else -> collection.document()
        }.set(toData(t)).await()
    }

    override suspend fun update(t: T) {
        collection.document(getId(t)).update(toData(t)).await()
    }

    override suspend fun delete(t: T) {
        collection.document(getId(t)).delete().await()
    }

    private fun addListener(callback: CallBack<List<T>>): ListenerRegistration {
        return collection.addSnapshotListener { value, error ->
            error?.localizedMessage?.apply(callback.onError)
            value?.documents?.mapNotNull(transform)?.apply(callback.onSuccess)
        }
    }

}