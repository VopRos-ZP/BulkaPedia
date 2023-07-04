package com.bulkapedia.compose.screens.dashboard.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.data.CallBack
import com.bulkapedia.data.Repository
import com.bulkapedia.data.users.User
import com.bulkapedia.data.users.UsersRepository
import com.bulkapedia.data.sets.UserSet
import com.bulkapedia.data.mains.Main
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
    private val usersRepository: UsersRepository,
    private val setsRepository: Repository<UserSet>,
    private val statsRepository: Repository<Main>
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
            statsRepository.create(main, id)
        }
    }

    fun removeMainsHero(main: Main) {
        viewModelScope.launch { statsRepository.delete(main) }
    }

    fun removeUserSet(set: UserSet) {
        viewModelScope.launch { setsRepository.delete(set) }
    }

    fun fetchData() {
        usersListener = usersRepository.fetchAll {
            viewModelScope.launch { _usersFlow.emit(it) }
        }
        setsListener = setsRepository.fetchAll(CallBack({
            viewModelScope.launch { _setsFlow.emit(it) }
        }) {})
        statsListener = statsRepository.fetchAll(CallBack({
            viewModelScope.launch {
                _mainsFlow.emit(it.groupBy { s -> s.mainId.split(" ", limit = 2).first() })
            }
        }) {})
    }

    fun removeListeners() {
        usersListener?.let(usersRepository::remove)
        setsListener?.remove()
    }

}