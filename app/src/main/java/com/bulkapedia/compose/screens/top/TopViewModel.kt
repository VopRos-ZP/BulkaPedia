package com.bulkapedia.compose.screens.top

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    private val setsRepository: SetsRepository
) : ViewModel() {

    val sets: SnapshotStateList<UserSet> = mutableStateListOf()
    val set: SnapshotStateList<UserSet?> = mutableStateListOf(null)

    private var setsListener: ListenerRegistration? = null
    private var listener: ListenerRegistration? = null

    fun fetchSets(hero: String) {
        val id = hero.split("_")[0]
        setsListener = setsRepository.fetchAll { allSets ->
            val filtered = allSets
                .filter { it.hero == id }
                .sortedByDescending { it.userLikeIds.size }
                .take(100)
            sets.clear()
            sets.addAll(filtered)
        }
    }

    fun closeSet() {
        set.clear()
        set.add(null)
    }

    fun listenSet(setId: String) {
        listener = setsRepository.fetchAll { allSets ->
            set.clear()
            set.add(allSets.find { it.id == setId } ?: UserSet.EMPTY)
        }
    }

    fun removeListener() {
        setsListener?.remove()
        listener?.remove()
    }

}
