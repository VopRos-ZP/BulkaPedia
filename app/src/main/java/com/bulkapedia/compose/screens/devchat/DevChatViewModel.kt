package com.bulkapedia.compose.screens.devchat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.messages.Message
import com.bulkapedia.compose.data.repos.messages.MessagesRepository
import com.bulkapedia.compose.data.toDateTime
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevChatViewModel @Inject constructor(
    private val messagesRepository: MessagesRepository
) : ViewModel() {

    private val _messagesFlow: MutableStateFlow<List<Message>> = MutableStateFlow(emptyList())
    val messagesFlow: StateFlow<List<Message>> = _messagesFlow

    private var listener: ListenerRegistration? = null

    fun sendMessage(message: Message) {
        viewModelScope.launch { messagesRepository.create(message) }
    }

    fun fetchMessages(author: String, receiver: String) {
        listener = messagesRepository.fetchAll { msg ->
            val filtered = msg
                .filter { (it.author == author && it.receiver == receiver)
                        || (it.author == receiver && it.receiver == author) }
                .sortedWith { m1, m2 -> m1.date.toDateTime().compareTo(m2.date.toDateTime()) }
            viewModelScope.launch { _messagesFlow.emit(filtered) }
        }
    }

    fun removeListener() {
        listener?.remove()
    }

}
