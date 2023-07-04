package com.bulkapedia.compose.screens.mechanics.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.data.CallBack
import com.bulkapedia.data.mechanics.Mechanic
import com.bulkapedia.data.Repository
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MechanicViewModel @Inject constructor(
    private val mechanicsRepository: Repository<Mechanic>
) : ViewModel() {

    private val _mechanicFlow: MutableStateFlow<Mechanic?> = MutableStateFlow(null)
    val mechanicFlow: StateFlow<Mechanic?> = _mechanicFlow

    private var listener: ListenerRegistration? = null

    fun listenMechanic(id: String) {
        listener = mechanicsRepository.fetchAll(CallBack({ mechanics ->
            viewModelScope.launch {  _mechanicFlow.emit(mechanics.find { it.mechanicId == id }) }
        }) {})
    }

    fun removeListener() {
        listener?.remove()
    }

}