package com.bulkapedia.compose.screens.mechanics.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bulkapedia.Callback
import bulkapedia.StoreRepository
import bulkapedia.mechanics.Mechanic
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MechanicViewModel @Inject constructor(
    private val mechanicsRepository: StoreRepository<Mechanic>
) : ViewModel() {

    private val _mechanicFlow: MutableStateFlow<Mechanic?> = MutableStateFlow(null)
    val mechanicFlow: StateFlow<Mechanic?> = _mechanicFlow

    private var listener: ListenerRegistration? = null

    fun listenMechanic(id: String) {
        listener = mechanicsRepository.listenAll(Callback({ mechanics ->
            viewModelScope.launch {  _mechanicFlow.emit(mechanics.find { it.mechanicId == id }) }
        }))
    }

    fun removeListener() {
        listener?.remove()
    }

}