package bulkapedia.comments.utils

import bulkapedia.comments.Comment
import bulkapedia.comments.CommentDTO

fun Comment.toDTO(): CommentDTO = CommentDTO(commentId, author, setId, date, text)

fun CommentDTO.toPOJO(): Comment = Comment(id, author, setId, date, text)