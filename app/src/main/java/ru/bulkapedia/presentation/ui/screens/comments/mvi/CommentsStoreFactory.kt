package ru.bulkapedia.presentation.ui.screens.comments.mvi

import android.util.Log
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.coroutineExecutorFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.repository.CommentsRepository
import javax.inject.Inject

class CommentsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val commentsRepository: CommentsRepository
) {

    fun create(): CommentsStore = object : CommentsStore,
        Store<Comments.Intent, Comments.State, Comments.Label> by storeFactory.create<Comments.Intent, Comments.Action, Comments.Msg, Comments.State, Comments.Label>(
            name = "CommentsStore",
            initialState = Comments.State(),
            bootstrapper = coroutineBootstrapper {
                val handler = CoroutineExceptionHandler { _, throwable ->
                    Log.e("CommentsStore", throwable.stackTraceToString())

                    dispatch(Comments.Action.Error(throwable.localizedMessage))
                }
                launch(handler) {
                    commentsRepository.comments.collect {
                        dispatch(Comments.Action.Comments(it))
                    }
                }
            },
            executorFactory = coroutineExecutorFactory {
                onAction<Comments.Action.Comments> { dispatch(Comments.Msg.Comments(it.value)) }
                onAction<Comments.Action.Error> { dispatch(Comments.Msg.Error(it.value)) }
                onAction<Comments.Action.Loading> { dispatch(Comments.Msg.Loading(it.value)) }
                onAction<Comments.Action.UserChanged> { dispatch(Comments.Msg.UserChanged(it.value)) }

                onIntent<Comments.Intent.OnSendCommentClick> {
                    launch { commentsRepository.upsert(it.comment) }
                }
                onIntent<Comments.Intent.OnTextChanged> { dispatch(Comments.Msg.TextChanged(it.text)) }
                onIntent<Comments.Intent.OnDeleteCommentClick> {
                    launch { commentsRepository.delete(it.comment) }
                }
                onIntent<Comments.Intent.OnCloseError> { dispatch(Comments.Msg.Error(null)) }
            },
            reducer = {
                when (it) {
                    is Comments.Msg.UserChanged -> copy(user = it.value)
                    is Comments.Msg.Error -> copy(error = it.value)
                    is Comments.Msg.Loading -> copy(isLoading = it.value)
                    is Comments.Msg.Comments -> copy(comments = it.value)
                    is Comments.Msg.TextChanged -> copy(text = it.value)
                }
            }
        ) {}

}