package bulkapedia.instances

import bulkapedia.comments.Comment
import bulkapedia.comments.CommentDTO
import bulkapedia.comments.utils.toDTO
import bulkapedia.comments.utils.toPOJO
import com.google.firebase.firestore.CollectionReference

class CommentsRepositoryImpl(ref: CollectionReference) : RepositoryImpl<Comment>(
    ref, { it.toObject(CommentDTO::class.java)?.toPOJO() },
    { it.commentId }, { it.toDTO() }
)