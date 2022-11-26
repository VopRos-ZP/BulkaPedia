package com.bulkapedia.fragments

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulkapedia.R
import com.bulkapedia.databinding.CallDevViewBinding
import com.bulkapedia.models.ChatModel
import com.bulkapedia.recycler.ChatRecyclerAdapter
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class DevChatFragment : Fragment() {

    private lateinit var bind: CallDevViewBinding
    private lateinit var launcher: ActivityResultLauncher<Intent>

    private val args: DevChatFragmentArgs by navArgs()

    private lateinit var chatRef: CollectionReference

    private lateinit var messagesAdapter: ChatRecyclerAdapter
    private val messages = mutableListOf<ChatModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = CallDevViewBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatRef = Firebase.firestore.collection("chat")
        bind.include.actionBar.apply {
            setTitle(R.string.chat)
            setNavigationIcon(R.drawable.backspace)
            setNavigationOnClickListener { findNavController().navigateUp() }
        }
        messagesAdapter = ChatRecyclerAdapter(messages, this@DevChatFragment)
            bind.chatRecycler.layoutManager = LinearLayoutManager(context)
            bind.chatRecycler.adapter = messagesAdapter
            launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { res ->
                if (res.resultCode == RESULT_OK && res.data != null) {
                    val uri = res.data!!.data!!
                    val fileName = getFileName(bind.root.context.contentResolver, uri)
                    val path = StringBuilder(args.sender.email!!).append("/").append(fileName).toString()
                    val ref = FirebaseStorage.getInstance().reference.child(path)
                    val uploadTask = ref.putFile(uri)
                    uploadTask.continueWithTask {
                        return@continueWithTask ref.downloadUrl
                    }.addOnSuccessListener { storageUri ->
                        sendImage(args.sender.nickname!!, args.receiver.nickname!!, getDate(), storageUri.toString())
                    }
                }
            }
            bind.addPictureBtn.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                launcher.launch(intent)
            }
            bind.sendBtn.setOnClickListener {
                val text = bind.chatEditText.text.toString()
                if (text.isEmpty()) return@setOnClickListener
                sendMessage(args.sender.nickname!!, args.receiver.nickname!!, getDate(), text, "")
                bind.chatEditText.text.clear()
            }
        // Add listener
        chatRef
            .whereEqualTo("author", args.sender.nickname)
            .whereEqualTo("receiver", args.receiver.nickname)
            .addSnapshotListener(eventListener)
        chatRef
            .whereEqualTo("author", args.receiver.nickname)
            .whereEqualTo("receiver", args.sender.nickname)
            .addSnapshotListener(eventListener)
    }

    private fun sendImage(author: String, receiver: String, date: String, image: String) {
        sendMessage(author, receiver, date, "", image)
    }

    private fun sendMessage(author: String, receiver: String, date: String, text: String, image: String) {
        chatRef.add(ChatModel(author, receiver, date, text, image))
    }

    @SuppressLint("NotifyDataSetChanged")
    private val eventListener = EventListener<QuerySnapshot> { value, error ->
        if (error != null) return@EventListener
        if (value != null) {
            val count = messages.size
            var isModified = false
            var isRemoved = false
            var mIndex = -1
            var rIndex = -1
            for (docChange in value.documentChanges) {
                val model = getModelByDoc(docChange.document)
                if (docChange.type == DocumentChange.Type.ADDED) {
                    if (!messages.contains(model))
                        messages.add(model)
                } else if (docChange.type == DocumentChange.Type.REMOVED) {
                    isRemoved = true
                    rIndex = messages.indexOf(model)
                    messages.remove(model)
                } else if (docChange.type == DocumentChange.Type.MODIFIED) {
                    isModified = true
                    mIndex = messages.map { it.date }.indexOf(model.date)
                    if (mIndex >= 0)
                        messages[mIndex] = model
                }
            }
            messages.sortWith { obj1, obj2 ->
                obj1.date.compareTo(obj2.date)
            }
            if (count == 0) {
                messagesAdapter.notifyDataSetChanged()
            } else if (isModified && mIndex >= 0) {
                messagesAdapter.notifyItemChanged(mIndex)
            } else if (isRemoved && rIndex >= 0) {
                messagesAdapter.notifyItemRemoved(rIndex)
            } else {
                messagesAdapter.notifyItemRangeInserted(messages.size, messages.size)
                bind.chatRecycler.smoothScrollToPosition(messages.size - 1)
            }
        }
    }

    private fun getModelByDoc(doc: QueryDocumentSnapshot): ChatModel {
        return ChatModel(
            doc.getString("author")!!,
            doc.getString("receiver")!!,
            doc.getString("date")!!,
            doc.getString("text")!!,
            doc.getString("image")!!
        )
    }

    @SuppressLint("Range")
    fun getFileName(contentResolver: ContentResolver, image: Uri): String {
        var result: String? = null
        if (image.scheme.equals("content")) {
            try {
                val cursor = contentResolver.query(image, null, null, null)
                cursor.use {
                    if (it != null && it.moveToFirst()) {
                        result = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    }
                    it?.close()
                }
            } catch (_: Exception) {}
        }
        if (result == null) {
            result = image.path
            val cut = result!!.lastIndexOf("/")
            if (cut != -1)
                result = result!!.substring(cut + 1)
        }
        return result!!
    }

    private fun getDate(): String {
        val calendar = Calendar.getInstance(Locale.getDefault())
        return DateFormat.format("dd.MM.yyyy HH:mm:ss", calendar).toString()
    }

}