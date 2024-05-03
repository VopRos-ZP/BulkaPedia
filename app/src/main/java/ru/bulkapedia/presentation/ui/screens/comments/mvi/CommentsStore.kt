package ru.bulkapedia.presentation.ui.screens.comments.mvi

import com.arkivanov.mvikotlin.core.store.Store

interface CommentsStore : Store<Comments.Intent, Comments.State, Comments.Label>
