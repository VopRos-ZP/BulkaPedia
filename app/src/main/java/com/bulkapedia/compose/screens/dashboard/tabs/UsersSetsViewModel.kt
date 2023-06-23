package com.bulkapedia.compose.screens.dashboard.tabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.database.User
import com.bulkapedia.compose.data.repos.database.UsersRepository
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersSetsViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val setsRepository: SetsRepository
) : ViewModel() {

    private val _usersFlow: MutableStateFlow<List<User>> = MutableStateFlow(emptyList())
    val usersFlow: StateFlow<List<User>> = _usersFlow

    private val _setsFlow: MutableStateFlow<List<UserSet>> = MutableStateFlow(emptyList())
    val setsFlow: StateFlow<List<UserSet>> = _setsFlow

    private var usersListener: ValueEventListener? = null
    private var setsListener: ListenerRegistration? = null

    fun removeMainsHero(user: User, hero: String) {
        user.mains?.remove(hero)
        usersRepository.update(user) {}
    }

    fun removeUserSet(set: UserSet) {
        setsRepository.delete(set)
    }

    fun fetchData() {
        usersListener = usersRepository.fetchAll {
            viewModelScope.launch { _usersFlow.emit(it) }
        }
        setsListener = setsRepository.fetchAll {
            viewModelScope.launch { _setsFlow.emit(it) }
        }
    }

    fun removeListeners() {
        usersListener?.let(usersRepository::remove)
        setsListener?.remove()
    }

}