package com.bulkapedia.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bulkapedia.MAIN
import com.bulkapedia.databinding.ItemContainerReceivedCommentBinding
import com.bulkapedia.databinding.ItemContainerSendCommentBinding
import com.bulkapedia.databinding.ItemImageReceivedBinding
import com.bulkapedia.databinding.ItemImageSendBinding
import com.bulkapedia.fragments.DevChatFragment
import com.bulkapedia.models.ChatModel
import com.bumptech.glide.Glide

class ChatRecyclerAdapter (
    private var messages: MutableList<ChatModel>,
    private var fragment: DevChatFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_SEND_TEXT = 1
        const val VIEW_TYPE_SEND_IMAGE = 2
        const val VIEW_TYPE_RECEIVED_TEXT = 3
        const val VIEW_TYPE_RECEIVED_IMAGE = 4
    }

    private val holders = mutableListOf<RecyclerView.ViewHolder>()

    class SendRecyclerHolder(
        val bind: ItemContainerSendCommentBinding
        ) : RecyclerView.ViewHolder(bind.root)

    class ReceivedRecyclerHolder(
        val bind: ItemContainerReceivedCommentBinding
        ) : RecyclerView.ViewHolder(bind.root)

    class SendImageRecyclerHolder(
        val bind: ItemImageSendBinding
        ) : RecyclerView.ViewHolder(bind.root)

    class ReceivedImageRecyclerHolder(
        val bind: ItemImageReceivedBinding
    ) : RecyclerView.ViewHolder(bind.root)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder = when (viewType) {
            VIEW_TYPE_SEND_TEXT -> {
                val binding = ItemContainerSendCommentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                SendRecyclerHolder(binding)
            }
            VIEW_TYPE_SEND_IMAGE -> {
                val binding = ItemImageSendBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                SendImageRecyclerHolder(binding)
            }
            VIEW_TYPE_RECEIVED_TEXT -> {
                val binding = ItemContainerReceivedCommentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                ReceivedRecyclerHolder(binding)
            }
            else -> {
                val binding = ItemImageReceivedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                ReceivedImageRecyclerHolder(binding)
            }
        }
        holders.add(holder)
        return holder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        when (holder.itemViewType) {
            VIEW_TYPE_SEND_TEXT -> {
                (holder as SendRecyclerHolder).bind.apply {
                    textDateTime.text = message.date
                    textComment.text = message.text
                }
            }
            VIEW_TYPE_SEND_IMAGE -> {
                (holder as SendImageRecyclerHolder).bind.apply {
                    textDateTime.text = message.date
                    Glide.with(fragment).load(message.image).into(image)
                }
            }
            VIEW_TYPE_RECEIVED_TEXT -> {
                (holder as ReceivedRecyclerHolder).bind.apply {
                    textName.text = message.author
                    textComment.text = message.text
                    textDateTime.text = message.date
                }
            }
            else -> {
                (holder as ReceivedImageRecyclerHolder).bind.apply {
                    textDateTime.text = message.date
                    textName.text = message.author
                    Glide.with(fragment).load(message.image).into(image)
                }
            }
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].author == MAIN.prefs.getNickname()) {
            if (messages[position].image.isEmpty()) VIEW_TYPE_SEND_TEXT
            else VIEW_TYPE_SEND_IMAGE
        } else {
            if (messages[position].image.isEmpty()) VIEW_TYPE_RECEIVED_TEXT
            else VIEW_TYPE_RECEIVED_IMAGE
        }
    }

}