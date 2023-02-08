package com.bulkapedia.compose.screens.dashboard.tabs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.User
import com.bulkapedia.compose.data.User.Companion.toUser
import com.bulkapedia.compose.data.sets.UserSet
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UsersSetsViewModel @Inject constructor() : ViewModel() {

    val usersData: MutableLiveData<List<User>> = MutableLiveData(emptyList())
    val setsData: MutableLiveData<List<UserSet>> = MutableLiveData(emptyList())

    private var setsListener: ListenerRegistration? = null

    fun addMainsToUser(user: User) {
        val data = mapOf(
            "email" to user.email,
            "password" to user.password,
            "nickname" to user.nickname,
            "mains" to user.mains
        )
        Firebase.database.reference.child("users")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (ch in snapshot.children) {
                        ch.toUser()?.let { u ->
                            if (u.email == user.email && u.nickname == user.nickname) {
                                Firebase.database.reference.child("users")
                                    .child(ch.key!!).updateChildren(data)
                            }
                        }
                    }
                }
                override fun onCancelled(error: DatabaseError) {}
            })
    }

    fun listenUsers() {
        Database().addUsersSnapshotListener(usersData::postValue)
    }

    fun listenSets() {
        setsListener = Database().addSetsSnapshotListener(setsData::postValue)
    }

    fun removeListeners() {
        setsListener?.remove()
    }

}