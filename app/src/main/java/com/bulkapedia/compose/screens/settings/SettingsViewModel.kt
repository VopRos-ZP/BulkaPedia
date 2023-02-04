package com.bulkapedia.compose.screens.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class SettingsViewState {
    object Loading: SettingsViewState()
    data class Enter(val user: User): SettingsViewState()
    data class Error(val message: String): SettingsViewState()
}

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {

    private val _liveData: MutableLiveData<SettingsViewState> = MutableLiveData(SettingsViewState.Loading)
    val liveData: LiveData<SettingsViewState> = _liveData

    fun fetchUser(nickname: String) {
        Database().findUserByNickname(nickname, {
            if (it == "Пользователь не найден") {
                _liveData.postValue(SettingsViewState.Loading)
            } else {
                _liveData.postValue(SettingsViewState.Error(it))
            }
        }) { _, user ->
            _liveData.postValue(SettingsViewState.Enter(user))
        }
    }

}
