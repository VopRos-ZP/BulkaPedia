package ru.bulkapedia.presentation.ui.screens.comments.mvi

import ru.bulkapedia.domain.model.Comment
import ru.bulkapedia.domain.model.User

object Comments {

    data class State(
        val isLoading: Boolean = false,
        val user: User? = null,
        val comments: List<Comment> = emptyList(),
        val text: String = "",
        val error: String? = null
    )

    sealed interface Intent {
        data object OnNavigationBackClick : Intent
        data object OnCloseError : Intent
        data class OnSendCommentClick(val comment: Comment) : Intent
        data class OnCommentInfoClick(val comment: Comment) : Intent
        data class OnShowProfileClick(val user: User) : Intent
        data class OnDeleteCommentClick(val comment: Comment) : Intent
        data class OnTextChanged(val text: String) : Intent
    }

    sealed interface Label {
        data object NavigationBack : Label
        data class NavigationProfile(val user: User) : Label
    }

    sealed interface Msg {
        data class Loading(val value: Boolean) : Msg
        data class UserChanged(val value: User?) : Msg
        data class Comments(val value: List<Comment>) : Msg
        data class TextChanged(val value: String) : Msg
        data class Error(val value: String?) : Msg
    }

    sealed interface Action {
        data class Loading(val value: Boolean) : Action
        data class UserChanged(val value: User?) : Action
        data class Comments(val value: List<Comment>) : Action
        data class Error(val value: String?) : Action
    }

}