package com.bulkapedia.compose.screens.dashboard.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.database.User
import com.bulkapedia.compose.data.repos.database.UsersRepository
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.data.repos.stats.Stats
import com.bulkapedia.compose.data.repos.stats.StatsRepository
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class UsersSetsViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val setsRepository: SetsRepository,
    private val statsRepository: StatsRepository
) : ViewModel() {

    private val _usersFlow: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val usersFlow: StateFlow<List<User>> = _usersFlow

    private val _setsFlow = MutableStateFlow<List<UserSet>>(emptyList())
    val setsFlow = _setsFlow.asStateFlow()

    private val _mainsFlow = MutableStateFlow<Map<String, List<Stats>>>(emptyMap())
    val mainsFlow = _mainsFlow.asStateFlow()

    private var usersListener: ValueEventListener? = null
    private var setsListener: ListenerRegistration? = null
    private var statsListener: ListenerRegistration? = null

    fun addMain(id: String, stats: Stats) {
        viewModelScope.launch {
            statsRepository.create(stats, id).await()
        }
    }

    fun removeMainsHero(stats: Stats) {
        viewModelScope.launch { statsRepository.delete(stats).await() }
    }

    fun removeUserSet(set: UserSet) {
        viewModelScope.launch { setsRepository.delete(set).await() }
    }

    fun fetchData() {
        usersListener = usersRepository.fetchAll {
            viewModelScope.launch { _usersFlow.emit(it) }
        }
        setsListener = setsRepository.fetchAll {
            viewModelScope.launch { _setsFlow.emit(it) }
        }
        statsListener = statsRepository.fetchAll {
            viewModelScope.launch {
                _mainsFlow.emit(it.groupBy { s -> s.id.split(" ", limit = 2).first() })
            }
        }
    }

    fun removeListeners() {
        usersListener?.let(usersRepository::remove)
        setsListener?.remove()
    }

}