package com.bulkapedia.compose.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.classes.ChangeValue
import com.bulkapedia.compose.data.classes.Value
import com.bulkapedia.compose.data.now
import com.bulkapedia.compose.data.repos.database.User
import com.bulkapedia.compose.data.repos.database.UsersRepository
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.toYearDate
import com.bulkapedia.compose.data.yearFormat
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val usersRepository: UsersRepository,
    private val setsRepository: SetsRepository
) : ViewModel() {

    private val _userFlow: MutableStateFlow<User?> = MutableStateFlow(null)
    val userFlow: StateFlow<User?> = _userFlow

    private var listener: ValueEventListener? = null

    fun fetchUser(nickname: String) {
        listener = usersRepository.fetchAll { all ->
            viewModelScope.launch { _userFlow.emit(all.find { it.nickname == nickname }) }
        }
    }

    fun logout(onSuccess: suspend () -> Unit = {}) {
        usersRepository.logout()
        viewModelScope.launch { onSuccess() }
    }

    fun changeEmail(user: User, changeValue: ChangeValue, onSuccess: suspend (User) -> Unit): ChangeValue {
        return change(user, changeValue,
            listOf("почты", "почту", "Почта"),
            user.updateEmail.toYearDate(),
            user.email, { s, u -> u.apply { email = s } }) { old, it ->
            setsRepository.fetchAll { all ->
                all.filter { s -> s.userLikeIds.contains(old) }.forEach { s ->
                    s.userLikeIds.remove(it.email)
                    s.userLikeIds.add(it.email)
                    setsRepository.update(s)
                }
            }
            viewModelScope.launch { onSuccess(it) }
        }
    }

    fun changeNickname(user: User, changeValue: ChangeValue, onSuccess: suspend (User) -> Unit): ChangeValue {
        return change(user, changeValue,
            listOf("ника", "ник", "Ник"),
            user.updateNickname.toYearDate(),
            user.nickname, { s, u -> u.apply { nickname = s } }) { old, it ->
            setsRepository.fetchAll { all ->
                all.filter { s -> s.from == old }.forEach { s ->
                    s.from = it.nickname
                    setsRepository.update(s)
                }
            }
            viewModelScope.launch { onSuccess(it) }
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
                usersRepository.update(update(v.v.value, user)) {
                    onSuccess(value, it)
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
        listener?.let(usersRepository::remove)
    }

}
