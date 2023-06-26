package com.bulkapedia.compose.screens.profile

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.database.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.bulkapedia.compose.data.repos.database.UsersRepository
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.data.repos.stats.Stats
import com.bulkapedia.compose.data.repos.stats.StatsRepository
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val setsRepository: SetsRepository,
    private val statsRepository: StatsRepository
): ViewModel() {

    private val _userFlow = MutableStateFlow<User?>(null)
    val userFlow = _userFlow.asStateFlow()

    val userSets = mutableStateListOf<UserSet>()
    val userMains = mutableStateListOf<Stats>()

    private var listener: ValueEventListener? = null
    private var setListener: ListenerRegistration? = null
    private var mainsListener: ListenerRegistration? = null

    fun fetchUser(by: (User) -> Boolean) {
        listener = usersRepository.fetchAll { all ->
            viewModelScope.launch { _userFlow.emit(all.find(by)) }
        }
    }

    fun fetchMains(email: String) {
        mainsListener = statsRepository.fetchAll { stats ->
            userMains.clear()
            userMains.addAll(stats.filter { it.id.startsWith(email) })
        }
    }

    fun deleteSet(set: UserSet) {
        viewModelScope.launch { setsRepository.delete(set).await() }
    }

    fun filterSets(filter: (UserSet) -> Boolean) {
        setListener = setsRepository.fetchAll { all ->
            userSets.clear()
            userSets.addAll(all.filter(filter))
        }
    }

    fun removeListener() {
        listener?.let(usersRepository::remove)
        setListener?.remove()
        mainsListener?.remove()
    }

}