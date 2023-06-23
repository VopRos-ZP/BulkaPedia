package com.bulkapedia.compose.screens.mechanics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.mechanics.Mechanic
import com.bulkapedia.compose.data.repos.mechanics.MechanicsRepository
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MechanicsViewModel @Inject constructor(
    private val mechanicsRepository: MechanicsRepository
) : ViewModel() {

    private val _mechanicsFlow: MutableStateFlow<List<Mechanic>> = MutableStateFlow(emptyList())
    val mechanicsFlow: StateFlow<List<Mechanic>> = _mechanicsFlow

    private var listener: ListenerRegistration? = null

    fun listenMechanics() {
        listener = mechanicsRepository.fetchAll {
            viewModelScope.launch { _mechanicsFlow.emit(it) }
        }
    }

    fun removeListener() {
        listener?.remove()
    }

}
