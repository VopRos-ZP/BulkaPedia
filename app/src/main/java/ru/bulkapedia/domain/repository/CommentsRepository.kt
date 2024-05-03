package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.Comment

interface CommentsRepository {
    val comments: Flow<List<Comment>>
    suspend fun upsert(comment: Comment)
    suspend fun delete(comment: Comment)
}