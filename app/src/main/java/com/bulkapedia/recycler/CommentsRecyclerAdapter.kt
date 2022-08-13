package com.bulkapedia.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.MAIN
import com.bulkapedia.databinding.ItemContainerReceivedCommentBinding
import com.bulkapedia.databinding.ItemContainerSendCommentBinding
import com.bulkapedia.models.CommentModel

class CommentsRecyclerAdapter (
    private val comments: MutableList<CommentModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_SEND = 1
        const val VIEW_TYPE_RECEIVED = 2
    }

    class SendCommentRecyclerHolder(
        private val bind: ItemContainerSendCommentBinding
    ) : RecyclerView.ViewHolder(bind.root) {

        fun setData(data: CommentModel) {
            bind.textComment.text = data.text
            bind.textDateTime.text = data.date
        }

    }

    class ReceivedCommentRecyclerHolder(
        private val bind: ItemContainerReceivedCommentBinding
    ) : RecyclerView.ViewHolder(bind.root) {

        fun setData(data: CommentModel) {
            bind.textName.text = data.author
            bind.textComment.text = data.text
            bind.textDateTime.text = data.date
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SEND) {
            val binding = ItemContainerSendCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
            SendCommentRecyclerHolder(binding)
        } else {
            val binding = ItemContainerReceivedCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
            ReceivedCommentRecyclerHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val comment = comments[position]
        if (getItemViewType(position) == VIEW_TYPE_SEND) {
            (holder as SendCommentRecyclerHolder).setData(comment)
        } else {
            (holder as ReceivedCommentRecyclerHolder).setData(comment)
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (comments[position].author == MAIN.prefs.getNickname()) {
            VIEW_TYPE_SEND
        } else VIEW_TYPE_RECEIVED
    }

}