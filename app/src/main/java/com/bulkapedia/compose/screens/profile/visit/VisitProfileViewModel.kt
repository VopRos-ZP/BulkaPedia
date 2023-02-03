package com.bulkapedia.compose.screens.profile.visit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class VisitProfileViewState {
    object Loading: VisitProfileViewState()
    data class Enter(val user: User): VisitProfileViewState()
    data class Error(val message: String): VisitProfileViewState()
}

@HiltViewModel
class VisitProfileViewModel @Inject constructor() : ViewModel() {

    private val _liveData: MutableLiveData<VisitProfileViewState> = MutableLiveData(VisitProfileViewState.Loading)
    val liveData: LiveData<VisitProfileViewState> = _liveData

    fun fetchUser(nickname: String = "") {
        Database().addUsersSnapshotListener { users ->
            val find = users.find { it.nickname == nickname }
            if (find != null) {
                _liveData.postValue(VisitProfileViewState.Enter(find))
            } else {
                _liveData.postValue(VisitProfileViewState.Error("Не удалось получить пользователя"))
            }
        }
    }

}