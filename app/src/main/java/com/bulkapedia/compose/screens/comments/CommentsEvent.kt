package com.bulkapedia.compose.screens.comments

import com.bulkapedia.compose.data.Comment

sealed class CommentsEvent {
    data class LoadComments(val setId: String): CommentsEvent()
    data class OnSendComment(val comment: Comment): CommentsEvent()
    data class OnDeleteComment(val comment: Comment): CommentsEvent()
    data class OnEditComment(val comment: Comment, val newText: String): CommentsEvent()
}
