package com.vopros.data

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import com.vopros.data.util.addListener
import com.vopros.domain.Entity
import com.vopros.domain.Repository
import com.vopros.domain.complete.Complete
import com.vopros.domain.complete.VoidComplete

open class RepositoryImpl<T : Entity> (
    private val ref: CollectionReference,
    private val transform: (DocumentSnapshot) -> T?,
    private val errorMessage: String
) : Repository<T> {

    override fun fetchAll(callback: Complete<List<T>>): ListenerRegistration {
        return addListener(ref, transform, callback.success::invoke, callback.error::invoke)
    }

    override fun fetchById(id: String, callback: Complete<T>): ListenerRegistration {
        return addListener(ref, transform, { list ->
            checker(list.find { it.id == id }, callback.success::invoke, callback.error::invoke)
        }, callback.error::invoke)
    }

    override fun create(t: T, callback: Complete<T>) {
        ref.add(t.toData()).addOnCompleteListener { task ->
            reduceTask(task.result.get(), { ts ->
                checker(ts.result.let(transform), callback.success::invoke, callback.error::invoke)
            }, callback.error::invoke)
        }
    }

    override fun update(t: T, callback: Complete<T>) {
        ref.document(t.id).update(t.toData()).addOnCompleteListener { task ->
            reduceTask(task, { callback.success.invoke(t) }, callback.error::invoke)
        }
    }

    override fun delete(t: T, callback: VoidComplete) {
        ref.document(t.id).delete().addOnCompleteListener { task ->
            reduceTask(task, { callback.success.invoke() }, callback.error::invoke)
        }
    }

    private fun <R> reduceTask(task: Task<R>, onTrue: (Task<R>) -> Unit, onFalse: (String) -> Unit) {
        when (task.isSuccessful) {
            true -> onTrue.invoke(task)
            else -> task.exception?.message?.let(onFalse::invoke)
        }
    }

    private fun checker(t: T?, onSuccess: (T) -> Unit, onError: (String) -> Unit) {
        when (t) {
            null -> onError.invoke(errorMessage)
            else -> onSuccess.invoke(t)
        }
    }

}