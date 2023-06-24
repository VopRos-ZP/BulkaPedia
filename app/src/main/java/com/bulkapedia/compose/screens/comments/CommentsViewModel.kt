package com.bulkapedia.compose.screens.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.comments.Comment
import com.bulkapedia.compose.data.repos.comments.CommentsRepository
import com.bulkapedia.compose.data.repos.sets.SetsRepository
import com.bulkapedia.compose.data.repos.sets.UserSet
import com.bulkapedia.compose.data.toDateTime
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val setsRepository: SetsRepository,
    private val commentsRepository: CommentsRepository
) : ViewModel() {

    private val _setFlow: MutableStateFlow<UserSet?> = MutableStateFlow(null)
    val setFlow: StateFlow<UserSet?> = _setFlow

    private val _commentsFlow: MutableStateFlow<List<Comment>> = MutableStateFlow(emptyList())
    val commentsFlow: StateFlow<List<Comment>> = _commentsFlow

    private var setListener: ListenerRegistration? = null
    private var commentsListener: ListenerRegistration? = null

    fun addListener(setId: String) {
        setListener = setsRepository.fetchAll { allSets ->
            viewModelScope.launch { _setFlow.emit(allSets.find { it.id == setId } ) }
        }
        commentsListener = commentsRepository.fetchAll { allComments ->
            val filtered = allComments
                .filter { it.set == setId }
                .sortedWith { c1, c2 -> c1.date.toDateTime().compareTo(c2.date.toDateTime()) }
            viewModelScope.launch { _commentsFlow.emit(filtered) }
        }
    }

    fun sendComment(comment: Comment) {
        viewModelScope.launch { commentsRepository.create(comment).await() }
    }

    fun updateComment(comment: Comment) {
        commentsRepository.update(comment)
    }

    fun deleteSet(set: UserSet) {
        setsRepository.delete(set)
    }

    fun deleteComment(comment: Comment) {
        commentsRepository.delete(comment)
    }

    fun dispose() {
        setListener?.remove()
        commentsListener?.remove()
    }

}
