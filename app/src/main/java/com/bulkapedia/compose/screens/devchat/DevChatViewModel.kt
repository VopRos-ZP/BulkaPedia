package com.bulkapedia.compose.screens.devchat

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.User
import com.bulkapedia.compose.data.Message
import com.bulkapedia.compose.events.EventHandler
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

sealed class DevChatEvent {
    data class LoadMessages(val author: String, val receiver: String): DevChatEvent()
    data class SendMessage(val message: Message): DevChatEvent()
}

@HiltViewModel
class DevChatViewModel @Inject constructor() : ViewModel(), EventHandler<DevChatEvent> {

    val user: MutableLiveData<User> = MutableLiveData(null)
    val messages: MutableLiveData<List<Message>> = MutableLiveData(null)

    private var listener: ListenerRegistration? = null

    override fun obtainEvent(event: DevChatEvent) {
        when (event) {
            is DevChatEvent.LoadMessages -> fetchMessages(event.author, event.receiver)
            is DevChatEvent.SendMessage -> sendMessage(event.message)
        }
    }

    private fun sendMessage(message: Message) {
        Database().addMessage(message)
    }

    private fun fetchMessages(author: String, receiver: String) {
        listener = Database().addMessagesSnapshotListener { msg ->
            val filtered = msg
                .filter { (it.author == author && it.receiver == receiver)
                        || (it.author == receiver && it.receiver == author) }
                .sortedWith { obj1, obj2 -> getDate(obj1.date).compareTo(getDate(obj2.date)) }
            messages.postValue(filtered)
        }
    }

    fun userListener(author: String) {
        Database().addUsersSnapshotListener { users ->
            user.postValue(users.find { it.nickname == author })
        }
    }

    fun removeListener() {
        listener?.remove()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(strDate: String): Date {
        return try {
            SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(strDate)!!
        } catch (_: Exception) {
            Date()
        }
    }

}
