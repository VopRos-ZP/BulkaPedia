package ru.bulkapedia.data.models.comment

import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.tasks.await
import ru.bulkapedia.domain.model.Comment
import ru.bulkapedia.domain.model.User
import ru.bulkapedia.domain.model.UserSet
import ru.bulkapedia.domain.repository.CommentsRepository
import ru.bulkapedia.domain.utils.Table
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import javax.inject.Inject

class CommentsRepositoryImpl @Inject constructor() : CommentsRepository {

    private val table = Table.COMMENTS
    private val collection = Firebase.firestore.collection(table)

    override val comments: Flow<List<Comment>> = channelFlow {
        collection.snapshots().map { it.toObjects(CommentDto::class.java) }.collect { list ->
            val comments = list.map { dto ->
                val author = dto.author.snapshots().map { it.toObject(User::class.java) }.produceIn(this).receive()!!
                Comment(
                    id = dto.id,
                    author = author,
                    text = dto.text,
                    date = LocalDateTime.ofInstant(dto.date.toInstant(), ZoneId.systemDefault()),
                    set = dto.set
                )
            }
            send(comments)
        }
    }

    override suspend fun upsert(comment: Comment) {
        val userRef = Firebase.firestore.collection(Table.USERS).document(comment.author.id)
        val dto = CommentDto(
            id = comment.id,
            text = comment.text,
            author = userRef,
            set = comment.set,
            date = Timestamp(comment.date.toInstant(ZoneOffset.UTC))
        )
        when (comment.id.isEmpty()) {
            true -> collection.add(dto)
            else -> collection.document(comment.id).set(dto)
        }.await()
    }

    override suspend fun delete(comment: Comment) {
        collection.document(comment.id).delete().await()
    }
}