package com.bulkapedia.compose.screens.profile

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bulkapedia.Callback
import bulkapedia.StoreRepository
import bulkapedia.users.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import bulkapedia.sets.UserSet
import bulkapedia.mains.Main
import bulkapedia.users.UserRepository
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val usersRepository: UserRepository,
    private val setsRepository: StoreRepository<UserSet>,
    private val statsRepository: StoreRepository<Main>
): ViewModel() {

    private val _userFlow = MutableStateFlow<User?>(null)
    val userFlow = _userFlow.asStateFlow()

    val userSets = mutableStateListOf<UserSet>()
    val userMains = mutableStateListOf<Main>()

    private var listener: ValueEventListener? = null
    private var setListener: ListenerRegistration? = null
    private var mainsListener: ListenerRegistration? = null

    fun fetchUser(by: (User) -> Boolean) {
        listener = usersRepository.listenAll(Callback({ all ->
            viewModelScope.launch { _userFlow.emit(all.find(by)) }
        }))
    }

    fun fetchMains(email: String) {
        mainsListener = statsRepository.listenAll(Callback({ stats ->
            userMains.clear()
            userMains.addAll(stats.filter { it.mainId.startsWith(email) })
        }))
    }

    fun deleteSet(set: UserSet) {
        viewModelScope.launch { setsRepository.delete(set) }
    }

    fun filterSets(filter: (UserSet) -> Boolean) {
        setListener = setsRepository.listenAll(Callback({ all ->
            userSets.clear()
            userSets.addAll(all.filter(filter))
        }))
    }

    fun removeListener() {
        listener?.let(usersRepository::removeListener)
        setListener?.remove()
        mainsListener?.remove()
    }

}