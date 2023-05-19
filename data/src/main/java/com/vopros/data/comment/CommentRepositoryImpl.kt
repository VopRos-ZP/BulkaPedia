package com.vopros.data.comment

import com.vopros.data.Database
import com.vopros.data.RepositoryImpl
import com.vopros.domain.comment.Comment

class CommentRepositoryImpl : RepositoryImpl<Comment>(
    Database.comments, { it.toComment() } , "Комментарий не найден!"
)