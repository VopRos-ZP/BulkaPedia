package com.bulkapedia.recycler

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.databinding.ItemContainerReceivedCommentBinding
import com.bulkapedia.databinding.ItemContainerSendCommentBinding
import com.bulkapedia.fragments.CommentsFragment
import com.bulkapedia.fragments.CommentsFragmentDirections
import com.bulkapedia.models.CommentModel
import com.bulkapedia.utils.TripleButtonUtils

class CommentsRecyclerAdapter (
    private val comments: MutableList<CommentModel>,
    private val fragment: CommentsFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_SEND = 1
        const val VIEW_TYPE_RECEIVED = 2
    }

    private val holders = mutableListOf<RecyclerView.ViewHolder>()

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
        val holder = if (viewType == VIEW_TYPE_SEND) {
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
        holders.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val comment = comments[position]
        if (getItemViewType(position) == VIEW_TYPE_SEND) {
            (holder as SendCommentRecyclerHolder).setData(comment)
            holder.itemView.setOnLongClickListener {
                val wrapper = ContextThemeWrapper(MAIN, R.style.menuStyle_PopupMenu)
                val menu = PopupMenu(wrapper, it, Gravity.END)
                menu.inflate(R.menu.send_comments_menu)
                menu.setForceShowIcon(true)
                menu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.editItem -> {
                            fragment.editComment(comment)
                            true
                        }
                        R.id.deleteItem -> {
                            TripleButtonUtils.onClickConfirmAction {
                                Database().getCommentsNode()
                                    .whereEqualTo("set", comment.setId)
                                    .whereEqualTo("date", comment.date)
                                    .whereEqualTo("from", comment.author)
                                    .get().addOnSuccessListener { q ->
                                        q.documents[0].reference.delete().addOnSuccessListener {
                                            comments.remove(comment)
                                            holders.remove(holder)
                                    }
                                }
                            }
                            true
                        }
                        else -> false
                    }
                }
                menu.show()
                true
            }
        } else {
            (holder as ReceivedCommentRecyclerHolder).setData(comment)
            holder.itemView.setOnLongClickListener {
                val wrapper = ContextThemeWrapper(MAIN, R.style.menuStyle_PopupMenu)
                val menu = PopupMenu(wrapper, it)
                menu.inflate(R.menu.received_comments_menu)
                menu.setForceShowIcon(true)
                menu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.profileItem -> {
                            Database().retrieveUserByNickname(comment.author) { user ->
                                val action = CommentsFragmentDirections
                                    .actionCommentsFragmentToUserClientFragment(user, true)
                                fragment.findNavController().navigate(action)
                            }
                            true
                        }
                        else -> false
                    }
                }
                menu.show()
                true
            }
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