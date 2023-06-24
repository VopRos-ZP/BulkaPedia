package com.bulkapedia.compose.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.database.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.bulkapedia.compose.data.repos.database.UsersRepository
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val setsRepository: SetsRepository
): ViewModel() {

    private val _userFlow: MutableStateFlow<User?> = MutableStateFlow(null)
    val userFlow: StateFlow<User?> = _userFlow

    private val _userSetsFlow: MutableStateFlow<List<UserSet>> = MutableStateFlow(emptyList())
    val userSetsFlow: StateFlow<List<UserSet>> = _userSetsFlow

    private var listener: ValueEventListener? = null
    private var setListener: ListenerRegistration? = null

    fun fetchUser(by: (User) -> Boolean) {
        listener = usersRepository.fetchAll { all ->
            viewModelScope.launch { _userFlow.emit(all.find(by)) }
        }
    }

    fun deleteSet(set: UserSet) {
        viewModelScope.launch { setsRepository.delete(set).await() }
    }

    fun filterSets(filter: (UserSet) -> Boolean) {
        setListener = setsRepository.fetchAll { all ->
            viewModelScope.launch { _userSetsFlow.emit(all.filter(filter)) }
        }
    }

    fun removeListener() {
        listener?.let(usersRepository::remove)
        setListener?.remove()
    }

}