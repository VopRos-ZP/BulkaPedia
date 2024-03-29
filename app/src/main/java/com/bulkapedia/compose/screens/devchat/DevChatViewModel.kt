package com.bulkapedia.compose.screens.devchat

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.toDateTime
import bulkapedia.Callback
import bulkapedia.StoreRepository
import bulkapedia.devchat.Message
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevChatViewModel @Inject constructor(
    private val messagesRepository: StoreRepository<Message>
) : ViewModel() {

    val messages: SnapshotStateList<Message> = mutableStateListOf()

    private var listener: ListenerRegistration? = null

    fun sendMessage(message: Message) {
        viewModelScope.launch { messagesRepository.create(message) }
    }

    fun fetchMessages(author: String, receiver: String) {
        listener = messagesRepository.listenAll(Callback({ msg ->
            val filtered = msg
                .filter { (it.author == author && it.receiver == receiver)
                        || (it.author == receiver && it.receiver == author) }
                .sortedWith { m1, m2 -> m1.date.toDateTime().compareTo(m2.date.toDateTime()) }
            messages.clear()
            messages.addAll(filtered)
        }))
    }

    fun removeListener() {
        listener?.remove()
    }

}
