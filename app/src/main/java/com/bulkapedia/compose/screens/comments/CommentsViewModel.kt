package com.bulkapedia.compose.screens.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bulkapedia.StoreRepository
import bulkapedia.comments.Comment
import bulkapedia.sets.UserSet
import bulkapedia.Callback
import com.bulkapedia.compose.data.toDateTime
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val setsRepository: StoreRepository<UserSet>,
    private val commentsRepository: StoreRepository<Comment>
) : ViewModel() {

    private val _setFlow = MutableStateFlow<UserSet?>(null)
    val setFlow = _setFlow.asStateFlow()

    private val _commentsFlow = MutableStateFlow<List<Comment>>(emptyList())
    val commentsFlow = _commentsFlow.asStateFlow()

    private var setListener: ListenerRegistration? = null
    private var commentsListener: ListenerRegistration? = null

    fun addListener(setId: String) {
        setListener = setsRepository.listenAll(Callback({ allSets ->
            viewModelScope.launch { _setFlow.emit(allSets.find { it.setId == setId }) }
        }))
        commentsListener = commentsRepository.listenAll(Callback({ allComments ->
            val filtered = allComments
                .filter { it.setId == setId }
                .sortedWith { c1, c2 -> c1.date.toDateTime().compareTo(c2.date.toDateTime()) }
            viewModelScope.launch { _commentsFlow.emit(filtered) }
        }))
    }

    fun sendComment(comment: Comment) {
        viewModelScope.launch { commentsRepository.create(comment) }
    }

    fun updateComment(comment: Comment) {
        viewModelScope.launch { commentsRepository.update(comment) }
    }

    fun deleteSet(set: UserSet) {
        viewModelScope.launch { setsRepository.delete(set) }
    }

    fun deleteComment(comment: Comment) {
        viewModelScope.launch { commentsRepository.delete(comment) }
    }

    fun dispose() {
        setListener?.remove()
        commentsListener?.remove()
    }

}
