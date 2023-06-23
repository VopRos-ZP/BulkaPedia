package com.bulkapedia.compose.screens.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    private val setsRepository: SetsRepository
) : ViewModel() {

    private val _setsFlow: MutableStateFlow<List<UserSet>> = MutableStateFlow(emptyList())
    val setsFlow: StateFlow<List<UserSet>> = _setsFlow

    private val _setFlow: MutableStateFlow<UserSet?> = MutableStateFlow(null)
    val setFlow: StateFlow<UserSet?> = _setFlow

    private var setsListener: ListenerRegistration? = null
    private var listener: ListenerRegistration? = null

    fun fetchSets(hero: String) {
        val id = hero.split("_")[0]
        setsListener = setsRepository.fetchAll { allSets ->
            val filtered = allSets
                .filter { it.hero == id }
                .sortedByDescending { it.likes }
                .take(100)
            viewModelScope.launch { _setsFlow.emit(filtered) }
        }
    }

    fun closeSet() {
        viewModelScope.launch { _setFlow.emit(null) }
    }

    fun listenSet(setId: String) {
        listener = setsRepository.fetchAll { allSets ->
            viewModelScope.launch { _setFlow.emit(allSets.find { it.id == setId }) }
        }
    }

    fun removeListener() {
        setsListener?.remove()
        listener?.remove()
    }

}
