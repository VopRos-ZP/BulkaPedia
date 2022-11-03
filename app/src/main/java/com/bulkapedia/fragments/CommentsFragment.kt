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
        args.set.apply {
            val ivGears = listOf(
                bind.heroSetInclude.setInclude.ivHead, bind.heroSetInclude.setInclude.ivBody,
                bind.heroSetInclude.setInclude.ivArm, bind.heroSetInclude.setInclude.ivLeg,
                bind.heroSetInclude.setInclude.ivDecor, bind.heroSetInclude.setInclude.ivDevice
            )
            val gears = getGears()
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
            if (args.set.from != MAIN.prefs.getNickname() || !MAIN.prefs.getSigned()) {
                bind.heroSetInclude.settingsProfileButton.apply {
                    // profile
                    setImageResource(R.drawable.person)
                    setOnClickListener {
                        Database().retrieveUserByNickname(args.set.from) {
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
                                    TripleButtonUtils.onClickEdit(args.set) {
                                        val action = CommentsFragmentDirections.actionCommentsFragmentToCreateUserSetFragment(it, args.set)
                                        findNavController().navigate(action)
                                    }
                                    true
                                }
                                R.id.deleteItem -> {
                                    TripleButtonUtils.onClickDelete(mutableListOf(args.set), args.set) {}
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

            if (containsId())
                bind.heroSetInclude.likesBox.setImageResource(R.drawable.liked)

            bind.heroSetInclude.likesCount.text = "$likes"
            bind.heroSetInclude.likesBox.setOnClickListener {
                if (from == MAIN.prefs.getNickname()) return@setOnClickListener
                if (containsId()) {
                    likes -= 1
                    removeId()
                    Database().addUserSet(this)
                    bind.heroSetInclude.likesCount.text = "$likes"
                    bind.heroSetInclude.likesBox.setImageResource(R.drawable.unliked)
                } else {
                    likes += 1
                    userLikeIds.add(MAIN.prefs.getEmail()!!)
                    Database().addUserSet(this)
                    bind.heroSetInclude.likesCount.text = "$likes"
                    bind.heroSetInclude.likesBox.setImageResource(R.drawable.liked)
                }
            }
            bind.commentsRecycler.layoutManager = LinearLayoutManager(context)
            commentAdapter = CommentsRecyclerAdapter(comments)
            bind.commentsRecycler.adapter = commentAdapter
            if (!MAIN.prefs.getSigned()) {
                bind.commentEditText.isEnabled = false
            }
            bind.sendBtn.setOnClickListener {
                // send comment to recycler and view
                val text = bind.commentEditText.text.toString()
                if (text.isEmpty()) return@setOnClickListener
                val calendar = Calendar.getInstance(Locale.getDefault())
                val date = DateFormat.format("dd.MM.yyyy HH:mm:ss", calendar).toString()
                val model = CommentModel(MAIN.prefs.getNickname()!!, date, text)
                val map = mapOf(
                    "hero" to hero,
                    "from" to model.author,
                    "date" to model.date,
                    "text" to model.text
                )
                firestore.collection("comments").add(map)
                bind.commentEditText.text.clear()
            }
            firestore.collection("comments")
                .whereEqualTo("hero", hero)
                .addSnapshotListener(eventListener)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private val eventListener = EventListener<QuerySnapshot> { value, error ->
        if (error != null) return@EventListener
        if (value != null) {
            val count = comments.size
            for (docChange in value.documentChanges) {
                if (docChange.type == DocumentChange.Type.ADDED) {
                    val doc = docChange.document
                    val model = CommentModel(
                        doc.getString("from")!!,
                        doc.getString("date")!!,
                        doc.getString("text")!!,
                    )
                    if (!comments.contains(model))
                        comments.add(model)
                }
            }
            comments.sortWith { obj1, obj2 ->
                obj1.date.compareTo(obj2.date)
            }
            if (count == 0) {
                commentAdapter.notifyDataSetChanged()
            } else {
                commentAdapter.notifyItemRangeInserted(comments.size, comments.size)
                bind.commentsRecycler.smoothScrollToPosition(comments.size - 1)
            }
        }
    }

    private fun getGears(): Map<GearCell, Gear?> {
        return args.set.gears.map { gs ->
            val index = GearsList.allGears.map{ it.icon }.indexOf(gs.value)
            if (index == -1)
                gs.key to null
            else
                gs.key to GearsList.allGears[index]
        }.toMap()
    }

    private fun containsId() : Boolean {
        for (id in args.set.userLikeIds)
            if (id == MAIN.prefs.getEmail())
                return true
        return false
    }

    private fun removeId() {
        for ((index, id) in args.set.userLikeIds.withIndex()) {
            if (id == MAIN.prefs.getEmail()) {
                args.set.userLikeIds.removeAt(index)
            }
        }
    }

}