package com.bulkapedia.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulkapedia.MAIN
import com.bulkapedia.R
import com.bulkapedia.database.Database
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.models.CommentModel
import com.bulkapedia.recycler.CommentsRecyclerAdapter
import com.bulkapedia.sets.GearCell
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.bulkapedia.databinding.CommentsFragmentBinding
import com.bulkapedia.sets.UserSet
import com.bulkapedia.utils.TripleButtonUtils
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import java.util.*

class CommentsFragment : Fragment() {

    lateinit var bind: CommentsFragmentBinding
    private val args: CommentsFragmentArgs by navArgs()

    private lateinit var commentAdapter: CommentsRecyclerAdapter
    private lateinit var database: FirebaseDatabase
    private lateinit var firestore: FirebaseFirestore

    private val comments = mutableListOf<CommentModel>()

    private var isEdit = false
    private lateinit var editComment: CommentModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = CommentsFragmentBinding.inflate(inflater, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database = Firebase.database
        firestore = Firebase.firestore
        Database().getSet(args.set.setId) { set ->
            val ivGears = listOf(
                bind.heroSetInclude.setInclude.ivHead, bind.heroSetInclude.setInclude.ivBody,
                bind.heroSetInclude.setInclude.ivArm, bind.heroSetInclude.setInclude.ivLeg,
                bind.heroSetInclude.setInclude.ivDecor, bind.heroSetInclude.setInclude.ivDevice
            )
            val gears = getGears(set)
            val cells = listOf(
                GearCell.HEAD, GearCell.BODY,
                GearCell.ARM, GearCell.LEG,
                GearCell.DECOR, GearCell.DEVICE,
            )
            cells.forEachIndexed { i, cell ->
                if (gears[cell] != null) {
                    ivGears[i].setImageResource(gears[cell]!!.icon)
                }
            }
            if (set.from != MAIN.prefs.getNickname() || !MAIN.prefs.getSigned()) {
                bind.heroSetInclude.settingsProfileButton.apply {
                    // profile
                    setImageResource(R.drawable.person)
                    setOnClickListener {
                        Database().retrieveUserByNickname(set.from) {
                            val action = CommentsFragmentDirections.actionCommentsFragmentToUserClientFragment(it, true)
                            findNavController().navigate(action)
                        }
                    }
                }
            } else {
                bind.heroSetInclude.settingsProfileButton.apply {
                    setImageResource(R.drawable.settings)
                    setOnClickListener {
                        val wrapper = ContextThemeWrapper(MAIN, R.style.menuStyle_PopupMenu)
                        val popupMenu = PopupMenu(wrapper, it)
                        popupMenu.inflate(R.menu.client_settings_menu)
                        popupMenu.setOnMenuItemClickListener { item ->
                            return@setOnMenuItemClickListener when (item.itemId) {
                                R.id.editItem -> {
                                    TripleButtonUtils.onClickEdit(args.set) { m ->
                                        val action = CommentsFragmentDirections.actionCommentsFragmentToCreateUserSetFragment(m, args.set)
                                        findNavController().navigate(action)
                                    }
                                    true
                                }
                                R.id.deleteItem -> {
                                    TripleButtonUtils.onClickDelete(mutableListOf(args.set), args.set) {}
                                    findNavController().navigateUp()
                                    true
                                }
                                else -> false
                            }
                        }
                        try {
                            val fieldPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                            fieldPopup.isAccessible = true
                            val mPopup = fieldPopup.get(popupMenu)
                            mPopup.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                                .invoke(mPopup, true)
                        } catch (_: Exception) {}
                        popupMenu.show()
                    }
                }
            }

            bind.heroSetInclude.commentButton.apply {
                setImageResource(R.drawable.backspace)
                setOnClickListener { findNavController().navigateUp() }
            }

            if (containsId(set))
                bind.heroSetInclude.likesBox.setImageResource(R.drawable.liked)

            bind.heroSetInclude.likesCount.text = "${set.likes}"
            bind.heroSetInclude.likesBox.setOnClickListener {
                if ((MAIN.prefs.getNickname() == set.from) || !MAIN.prefs.getSigned()) return@setOnClickListener
                if (containsId(set)) {
                    set.likes -= 1
                    removeId(set)
                    Database().addUserSet(set)
                    bind.heroSetInclude.likesCount.text = "${set.likes}"
                    bind.heroSetInclude.likesBox.setImageResource(R.drawable.unliked)
                } else {
                    set.likes += 1
                    set.userLikeIds.add(MAIN.prefs.getEmail()!!)
                    Database().addUserSet(set)
                    bind.heroSetInclude.likesCount.text = "${set.likes}"
                    bind.heroSetInclude.likesBox.setImageResource(R.drawable.liked)
                }
            }
            bind.commentsRecycler.layoutManager = LinearLayoutManager(context)
            commentAdapter = CommentsRecyclerAdapter(comments, this)
            bind.commentsRecycler.adapter = commentAdapter
            if (!MAIN.prefs.getSigned()) {
                bind.commentEditText.isEnabled = false
            }
            bind.sendBtn.setOnClickListener {
                // send comment to recycler and view
                val text = bind.commentEditText.text.toString()
                if (text.isEmpty()) return@setOnClickListener
                if (!isEdit) {
                    val calendar = Calendar.getInstance(Locale.getDefault())
                    val date = DateFormat.format("dd.MM.yyyy HH:mm:ss", calendar).toString()
                    val model = CommentModel(MAIN.prefs.getNickname()!!, date, text, set.setId)
                    val map = mapOf(
                        "set" to set.setId,
                        "from" to model.author,
                        "date" to model.date,
                        "text" to model.text
                    )
                    firestore.collection("comments").add(map)
                } else {
                    val map = mapOf(
                        "set" to editComment.setId,
                        "from" to editComment.author,
                        "date" to editComment.date,
                        "text" to text
                    )
                    Database().getCommentsNode()
                        .get().addOnSuccessListener { q ->
                            q.documents.find {
                                it["date"] == editComment.date &&
                                        it["set"] == editComment.setId &&
                                        it["from"] == editComment.author
                            }?.reference?.update(map)
                        }
                }
                bind.commentEditText.text.clear()
            }
            firestore.collection("comments")
                .whereEqualTo("set", set.setId)
                .addSnapshotListener(eventListener)
        }
    }

    fun editComment(comment: CommentModel) {
        isEdit = true
        editComment = comment
        bind.commentEditText.setText(editComment.text)
    }

    @SuppressLint("NotifyDataSetChanged")
    private val eventListener = EventListener<QuerySnapshot> { value, error ->
        if (error != null) return@EventListener
        if (value != null) {
            val count = comments.size
            var isModified = false
            var isRemoved = false
            var mIndex = -1
            var rIndex = -1
            for (docChange in value.documentChanges) {
                val model = getModelByDoc(docChange.document)
                if (docChange.type == DocumentChange.Type.ADDED) {
                    if (!comments.contains(model))
                        comments.add(model)
                } else if (docChange.type == DocumentChange.Type.REMOVED) {
                    isRemoved = true
                    rIndex = comments.indexOf(model)
                    comments.remove(model)
                } else if (docChange.type == DocumentChange.Type.MODIFIED) {
                    isModified = true
                    mIndex = comments.map {
                        mapOf(
                            "set" to it.setId,
                            "date" to it.date,
                            "from" to it.author
                        )
                    }.indexOf(mapOf(
                        "set" to model.setId,
                        "date" to model.date,
                        "from" to model.author
                    ))
                    if (mIndex >= 0)
                        comments[mIndex] = model
                }
            }
            comments.sortWith { obj1, obj2 ->
                obj1.date.compareTo(obj2.date)
            }
            if (count == 0) {
                commentAdapter.notifyDataSetChanged()
            } else if (isModified && mIndex >= 0) {
                commentAdapter.notifyItemChanged(mIndex)
            } else if (isRemoved && rIndex >= 0) {
                commentAdapter.notifyItemRemoved(rIndex)
            } else {
                commentAdapter.notifyItemRangeInserted(comments.size, comments.size)
                bind.commentsRecycler.smoothScrollToPosition(comments.size - 1)
            }
        }
    }

    private fun getModelByDoc(doc: QueryDocumentSnapshot): CommentModel {
        return CommentModel(
            doc.getString("from")!!,
            doc.getString("date")!!,
            doc.getString("text")!!,
            doc.getString("set")!!
        )
    }

    private fun getGears(set: UserSet): Map<GearCell, Gear?> {
        return set.gears.map { gs ->
            val index = GearsList.allGears.map{ it.icon }.indexOf(gs.value)
            if (index == -1)
                gs.key to null
            else
                gs.key to GearsList.allGears[index]
        }.toMap()
    }

    private fun containsId(set: UserSet) : Boolean {
        for (id in set.userLikeIds)
            if (id == MAIN.prefs.getEmail())
                return true
        return false
    }

    private fun removeId(set: UserSet) {
        for ((index, id) in set.userLikeIds.withIndex()) {
            if (id == MAIN.prefs.getEmail()) {
                set.userLikeIds.removeAt(index)
            }
        }
    }

}