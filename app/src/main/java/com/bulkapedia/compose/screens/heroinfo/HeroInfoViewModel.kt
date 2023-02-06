package com.bulkapedia.compose.screens.heroinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.Database
import com.bulkapedia.compose.data.category.HeroInfo
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroesInfoViewModel @Inject constructor() : ViewModel() {

    val heroesData: MutableLiveData<List<HeroInfo>> = MutableLiveData(emptyList())

    private var listener: ListenerRegistration? = null

    fun listenHeroInfo() {
        listener = Database().addHeroInfoSnapshotListener { heroInfos ->
            heroesData.postValue(heroInfos)
        }
    }

    fun removeListener() {
        listener?.remove()
    }

}

@HiltViewModel
class HeroInfoViewModel @Inject constructor() : ViewModel() {

    val heroData: MutableLiveData<Pair<HeroInfo?, Int>> = MutableLiveData(Pair(null, 0))

    private var listener: ListenerRegistration? = null

    fun listenHeroInfo(id: String) {
        listener = Database().addHeroInfoSnapshotListener { heroInfos ->
            heroData.postValue(Pair(heroInfos.find { it.id == id }, heroInfos.size))
        }
    }

    fun removeListener() {
        listener?.remove()
    }

}
