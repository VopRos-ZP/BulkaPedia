package com.bulkapedia.compose.screens.dashboard.screens.heroinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.category.HeroInfo
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroInfoViewModel @Inject constructor() : ViewModel() {

    val liveData: MutableLiveData<List<HeroInfo>> = MutableLiveData(emptyList())

    private var listener: ListenerRegistration? = null

    fun addHeroInfo(info: HeroInfo) {
        Database().addHeroInfo(info)
    }

    fun listenInfo() {
        listener = Database().addHeroInfoSnapshotListener(liveData::postValue)
    }

    fun removeListener() {
        listener?.remove()
    }

}