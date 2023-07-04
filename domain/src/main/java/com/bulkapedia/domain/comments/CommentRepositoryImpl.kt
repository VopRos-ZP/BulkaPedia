package com.bulkapedia.domain.comments

import com.bulkapedia.data.Repository
import com.bulkapedia.data.comments.Comment
import com.bulkapedia.data.comments.Comment.Companion.toComment
import com.bulkapedia.data.comments.Comment.Companion.toData
import com.bulkapedia.domain.core.Refs
import com.bulkapedia.domain.core.RepositoryImpl

class CommentRepositoryImpl : RepositoryImpl<Comment>(
    Refs.Store.comments,
    { it.toComment() },
    { it.commentId },
    { it.toData() }
)

typealias CommentRepository = Repository<Comment>