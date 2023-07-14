package com.bulkapedia.compose.screens.settings

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.classes.ChangeValue
import com.bulkapedia.compose.data.classes.Value
import com.bulkapedia.compose.data.now
import com.bulkapedia.compose.data.nowYearFormat
import com.bulkapedia.compose.data.toYearDate
import com.bulkapedia.compose.data.yearFormat
import bulkapedia.Callback
import bulkapedia.StoreRepository
import bulkapedia.sets.UserSet
import bulkapedia.users.User
import bulkapedia.users.UserRepository
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.Period
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val usersRepository: UserRepository,
    private val setsRepository: StoreRepository<UserSet>
) : ViewModel() {

    val user = mutableStateListOf<User?>(null)

    private var listener: ValueEventListener? = null

    fun fetchUser(email: String) {
        listener = usersRepository.listenAll(Callback({ all ->
            user.clear()
            user.add(all.find { it.email == email })
        }))
    }

    fun logout(onSuccess: suspend () -> Unit = {}) {
        usersRepository.logout()
        viewModelScope.launch { onSuccess() }
    }

    fun changeEmail(user: User, changeValue: ChangeValue, onSuccess: suspend (User) -> Unit): ChangeValue {
        return change(user, changeValue,
            listOf("почты", "почту", "Почта"),
            user.updateEmail.toYearDate().atTime(0, 0),
            user.email, { s, u -> u.copy(email = s, updateEmail = nowYearFormat()) }) { old, it ->
            setsRepository.listenAll(Callback({ all ->
                all.filter { s -> s.userLikeIds.contains(old) }.forEach { s ->
                    val ids = s.userLikeIds.toMutableList()
                    ids.remove(it.email)
                    ids.add(it.email)

                    val newSet = s.copy(userLikeIds = ids.distinct())
                    viewModelScope.launch { setsRepository.update(newSet) }
                }
            }))
            viewModelScope.launch { onSuccess(it) }
        }
    }

    fun changeNickname(user: User, changeValue: ChangeValue, onSuccess: suspend (User) -> Unit): ChangeValue {
        return change(user, changeValue,
            listOf("ника", "ник", "Ник"),
            user.updateNickname.toYearDate().atTime(0, 0),
            user.nickname, { s, u -> u.copy(nickname = s, updateNickname = nowYearFormat()) }) { old, it ->
            viewModelScope.launch {
                setsRepository.fetchAll().let { all ->
                    all.filter { s -> s.author == old }.forEach { s ->
                        val newSet = s.copy(author = it.nickname)
                        viewModelScope.launch { setsRepository.update(newSet) }
                    }
                }
                onSuccess(it)
            }
        }
    }

    private fun change(
        user: User, changeValue: ChangeValue,
        names: List<String>, lastUpdate: LocalDateTime,
        value: String,
        update: (String, User) -> User,
        onSuccess: (String, User) -> Unit
    ): ChangeValue {
        val period = Period.between(lastUpdate.toLocalDate(), now().toLocalDate()).toTotalMonths()
        if (period >= 2) {
            changeValue.infoText.value = "Последущая смена ${names[0]} будет возможна только через 2 месяца"
            changeValue.onSave.value = { newValue ->
                val v = newValue as Value.TextValue
                viewModelScope.launch {
                    usersRepository.update(update(v.v.value, user))
                    usersRepository.fetchById(user.userId)?.let { onSuccess(value, it) }
                }
            }
        } else {
            val newDate = lastUpdate.plusMonths(2 - period).format(yearFormat)
            changeValue.infoText.value = "Вы уже сменили ${names[1]} ${lastUpdate.format(yearFormat)}, " +
                    "смена будет доступна: " + newDate
            changeValue.onSave.value = {}
        }
        changeValue.title.value = "Изменить ${names[1]}"
        changeValue.value = Value.TextValue(mutableStateOf(value))
        changeValue.fieldLabel.value = names[2]
        return changeValue
    }

    fun dispose() {
        listener?.let(usersRepository::removeListener)
    }

}
