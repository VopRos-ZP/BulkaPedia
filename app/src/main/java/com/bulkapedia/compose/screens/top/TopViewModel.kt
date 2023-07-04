package com.bulkapedia.compose.screens.top

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.data.CallBack
import com.bulkapedia.data.sets.UserSet
import com.bulkapedia.data.Repository
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    private val setsRepository: Repository<UserSet>
) : ViewModel() {

    val sets: SnapshotStateList<UserSet> = mutableStateListOf()

    private val _setFlow = MutableStateFlow<UserSet?>(null)
    val setFlow = _setFlow.asStateFlow()

    private var setsListener: ListenerRegistration? = null
    private var listener: ListenerRegistration? = null

    fun fetchSets(hero: String) {
        val id = hero.split("_")[0]
        setsListener = setsRepository.fetchAll(CallBack({ allSets ->
            val filtered = allSets
                .filter { it.hero == id }
                .sortedByDescending { it.userLikeIds.size }
                .take(100)
            sets.clear()
            sets.addAll(filtered)
        }) {})
    }

    fun closeSet() {
        viewModelScope.launch { _setFlow.emit(null) }
    }

    fun listenSet(setId: String) {
        listener = setsRepository.fetchAll(CallBack({ allSets ->
            viewModelScope.launch { _setFlow.emit(allSets.find { it.userSetId == setId }) }
        }) {})
    }

    fun removeListener() {
        setsListener?.remove()
        listener?.remove()
    }

}
