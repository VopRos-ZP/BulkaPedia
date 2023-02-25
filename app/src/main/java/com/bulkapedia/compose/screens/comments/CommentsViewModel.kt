package com.bulkapedia.compose.screens.comments

import android.annotation.SuppressLint
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Comment
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.sets.GearCell
import com.bulkapedia.compose.data.sets.UserSet
import com.bulkapedia.compose.data.sets.UserSet.Companion.toUserSet
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

sealed class CommentsViewState {
    object Loading: CommentsViewState()
    data class Enter(val set: UserSet): CommentsViewState()
    data class Error(val message: String): CommentsViewState()
}

@HiltViewModel
class CommentsViewModel @Inject constructor() : ViewModel() {

    companion object {
        val emptySet = UserSet(
            "", "", "", mapOf(
                GearCell.HEAD to "empty_head", GearCell.BODY to "empty_body",
                GearCell.ARM to "empty_arm", GearCell.LEG to "empty_leg",
                GearCell.DECOR to "empty_decor", GearCell.DEVICE to "empty_device",
            ), 0, mutableListOf()
        )
    }

    val setLiveData: MutableLiveData<UserSet> = MutableLiveData(emptySet)

    private var setListener: ListenerRegistration? = null

    fun addListener(setId: String) {
        setListener = Firebase.firestore.collection("sets").document(setId).addSnapshotListener { value, _ ->
            setLiveData.postValue(value?.toUserSet() ?: emptySet)
        }
    }

    fun removeListener() { setListener?.remove() }

}

@HiltViewModel
class CommentsVM @Inject constructor() : ViewModel() {

    private val _liveData: MutableLiveData<SnapshotStateList<Comment>> = MutableLiveData(null)
    val liveData: LiveData<SnapshotStateList<Comment>> = _liveData

    private var listener: ListenerRegistration? = null

    fun sendComment(comment: Comment) {
        Database().addComment(comment)
    }

    fun updateComment(comment: Comment) {
        Database().updateComment(comment)
    }

    fun addListener(setId: String, state: SnapshotStateList<Comment>) {
        listener = Database().addCommentsSnapshotListener { list ->
            val comments = list
                .filter { it.set == setId }
                .sortedWith { obj1, obj2 -> getDate(obj1.date).compareTo(getDate(obj2.date)) }
            state.clear()
            state.addAll(comments)
            _liveData.postValue(state)
        }
    }

    fun removeListener() { listener?.remove() }

    @SuppressLint("SimpleDateFormat")
    private fun getDate(strDate: String): Date {
        return try {
            SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(strDate)!!
        } catch (_: Exception) {
            Date()
        }
    }

}
