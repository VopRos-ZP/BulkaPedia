package com.bulkapedia.compose.screens.sets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.data.Repository
import com.bulkapedia.data.sets.UserSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetTabViewModel @Inject constructor(
    private val setsRepository: Repository<UserSet>
) : ViewModel() {

    private fun updateSet(set: UserSet) {
        viewModelScope.launch { setsRepository.update(set) }
    }

    fun likeUserSet(
        isSign: Boolean,
        nickname: String,
        email: String,
        set: UserSet,
        onError: (String) -> Unit
    ) {
        if (isSign) {
            if (set.author != nickname) {
                val ids = set.userLikeIds.toMutableList()
                when (set.userLikeIds.contains(email)) {
                    true -> ids.remove(email)
                    else -> ids.add(email)
                }
                val newSet = set.copy(userLikeIds = ids.distinct())
                updateSet(newSet)
            }
        } else {
            onError("Чтобы поставить лайк, надо зарегистрироватся!")
        }
    }

}