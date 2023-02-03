package com.bulkapedia.compose.screens.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.events.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.bulkapedia.compose.data.User
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

sealed class ProfileViewState {
    object Loading: ProfileViewState()
    data class EnterScreen(val user: User): ProfileViewState()
    data class ErrorScreen(val message: String): ProfileViewState()
}

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel(), EventHandler<ProfileEvent> {

    private val _liveData: MutableLiveData<ProfileViewState> = MutableLiveData(ProfileViewState.Loading)
    val liveData: LiveData<ProfileViewState> = _liveData

    private var listener: ValueEventListener? = null

    override fun obtainEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.Loading -> fetchUsers(event.email)
        }
    }

    private fun fetchUsers(email: String) {
        listener = Database().addUsersSnapshotListener { users ->
            val find = users.find { it.email == email }
            if (find == null) {
                _liveData.postValue(ProfileViewState.ErrorScreen("Пользователья не существует"))
            } else {
                _liveData.postValue(ProfileViewState.EnterScreen(find))
            }
        }
    }

    fun removeListener() {
        listener?.let {
            Firebase.database.reference.child("users")
                .removeEventListener(it)
        }
    }

}