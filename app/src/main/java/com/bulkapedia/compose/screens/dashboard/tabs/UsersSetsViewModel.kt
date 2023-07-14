package com.bulkapedia.compose.screens.dashboard.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bulkapedia.Callback
import bulkapedia.StoreRepository
import bulkapedia.users.User
import bulkapedia.sets.UserSet
import bulkapedia.mains.Main
import bulkapedia.users.UserRepository
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersSetsViewModel @Inject constructor(
    private val usersRepository: UserRepository,
    private val setsRepository: StoreRepository<UserSet>,
    private val statsRepository: StoreRepository<Main>
) : ViewModel() {

    private val _usersFlow: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val usersFlow: StateFlow<List<User>> = _usersFlow

    private val _setsFlow = MutableStateFlow<List<UserSet>>(emptyList())
    val setsFlow = _setsFlow.asStateFlow()

    private val _mainsFlow = MutableStateFlow<Map<String, List<Main>>>(emptyMap())
    val mainsFlow = _mainsFlow.asStateFlow()

    private var usersListener: ValueEventListener? = null
    private var setsListener: ListenerRegistration? = null
    private var statsListener: ListenerRegistration? = null

    fun addMain(id: String, main: Main) {
        viewModelScope.launch {
            statsRepository.create(main.copy(mainId = id))
        }
    }

    fun removeMainsHero(main: Main) {
        viewModelScope.launch { statsRepository.delete(main) }
    }

    fun removeUserSet(set: UserSet) {
        viewModelScope.launch { setsRepository.delete(set) }
    }

    fun fetchData() {
        usersListener = usersRepository.listenAll(Callback({
            viewModelScope.launch { _usersFlow.emit(it) }
        }))
        setsListener = setsRepository.listenAll(Callback({
            viewModelScope.launch { _setsFlow.emit(it) }
        }))
        statsListener = statsRepository.listenAll(Callback({
            viewModelScope.launch {
                _mainsFlow.emit(it.groupBy { s -> s.mainId.split(" ", limit = 2).first() })
            }
        }))
    }

    fun removeListeners() {
        usersListener?.let(usersRepository::removeListener)
        setsListener?.remove()
    }

}