package com.vopros.data.util

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ListenerRegistration
import com.vopros.data.Database
import com.vopros.domain.Ranks
import com.vopros.domain.gear.Effect

fun autoFillGearEffects(effects: List<Effect>, ints: Map<Int, List<Int>>): Map<Ranks, List<Effect>> {
    val m = mutableMapOf<Ranks, List<Effect>>()
    Ranks.values().forEachIndexed { i, r ->
        m += r to effects.mapIndexed { ei, it ->
            Effect(ints[ei]!![i], it.percent, it.description)
        }
    }
    return m
}

fun <T> addListener(
    ref: CollectionReference,
    transform: (DocumentSnapshot) -> T?,
    onSuccess: (List<T>) -> Unit,
    onError: (String) -> Unit
): ListenerRegistration {
    return ref.addSnapshotListener { value, error ->
        if (value != null) {
            onSuccess.invoke(value.documents.mapNotNull(transform))
        } else {
            error?.message?.let(onError)
        }
    }
}
