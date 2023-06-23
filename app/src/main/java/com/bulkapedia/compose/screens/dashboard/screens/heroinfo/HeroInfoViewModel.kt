package com.bulkapedia.compose.screens.dashboard.screens.heroinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.data.repos.hero_info.HeroInfo
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroInfoViewModel @Inject constructor() : ViewModel() {

    fun addHeroInfo(info: HeroInfo) {

    }

    fun listenInfo() {

    }

    fun removeListener() {

    }

}