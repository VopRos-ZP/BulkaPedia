package com.vopros.bulkapedia.ui.components.userSet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vopros.bulkapedia.storage.DataStore
import com.vopros.bulkapedia.userSet.SetRepository
import com.vopros.bulkapedia.userSet.UserSet
import com.vopros.bulkapedia.userSet.UserSetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserSetCardViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val setRepository: SetRepository
) : ViewModel() {

    private val _config = MutableStateFlow<Pair<String, Boolean>?>(null)
    val config = _config.asStateFlow()

    fun fetchConfig() {
        viewModelScope.launch { dataStore.config.collect { _config.emit(it) } }
    }

    fun like(userSet: UserSetUseCase) {
        viewModelScope.launch {
            dataStore.config.collect { (token, isSign) ->
                if (isSign) {
                    val likes = with(userSet.set.liked.toMutableList()) {
                        when (contains(token)) {
                            true -> remove(token)
                            else -> add(token)
                        }
                        distinct()
                    }
                    setRepository.update(
                        UserSet(
                            userSet.set.id,
                            userSet.set.author,
                            userSet.set.gears,
                            userSet.set.hero,
                            likes
                        )
                    )
                }
            }
        }
    }

}