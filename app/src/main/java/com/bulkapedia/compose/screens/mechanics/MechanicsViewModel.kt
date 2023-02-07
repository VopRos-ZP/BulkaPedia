package com.bulkapedia.compose.screens.mechanics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.category.Mechanic
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import org.checkerframework.framework.qual.DefaultQualifier
import javax.inject.Inject

@HiltViewModel
class MechanicsViewModel @Inject constructor() : ViewModel() {

    val mechanicsData: MutableLiveData<List<Mechanic>> = MutableLiveData(emptyList())

    private var listener: ListenerRegistration? = null

    fun listenMechanics() {
        listener = Database().addMechanicsSnapshotListener(mechanicsData::postValue)
    }

    fun removeListener() {
        listener?.remove()
    }

}

@HiltViewModel
class MechanicViewModel @Inject constructor() : ViewModel() {

    val mechanic: MutableLiveData<Mechanic?> = MutableLiveData(null)

    private var listener: ListenerRegistration? = null

    fun listenMechanic(id: String) {
        listener = Database().addMechanicsSnapshotListener { mechanics ->
            mechanic.postValue(mechanics.find { it.id == id })
        }
    }

    fun removeListener() {
        listener?.remove()
    }

}
