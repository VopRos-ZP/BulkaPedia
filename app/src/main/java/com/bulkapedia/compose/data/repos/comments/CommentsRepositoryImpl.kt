package com.bulkapedia.compose.data.repos.comments

import com.bulkapedia.compose.data.repos.comments.Comment.Companion.toComment
import com.bulkapedia.compose.data.repos.firestore.FirestoreDB
import com.bulkapedia.compose.data.repos.firestore.FirestoreRepository
import com.bulkapedia.compose.data.repos.firestore.Repository

class CommentsRepositoryImpl : FirestoreRepository<Comment>(FirestoreDB.comments, { it.toComment() })

typealias CommentsRepository = Repository<Comment>