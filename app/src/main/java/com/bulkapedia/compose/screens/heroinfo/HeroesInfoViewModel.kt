package com.bulkapedia.compose.screens.heroinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.hero_info.HeroInfo
import com.bulkapedia.compose.data.repos.hero_info.HeroInfoRepository
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesInfoViewModel @Inject constructor(
    private val heroInfoRepository: HeroInfoRepository
) : ViewModel() {

    private val _heroesInfoFlow: MutableStateFlow<List<HeroInfo>> = MutableStateFlow(emptyList())
    val heroesInfoFlow: StateFlow<List<HeroInfo>> = _heroesInfoFlow

    private var listener: ListenerRegistration? = null

    fun listenHeroInfo() {
        listener = heroInfoRepository.fetchAll {
            viewModelScope.launch { _heroesInfoFlow.emit(it) }
        }
    }

    fun removeListener() {
        listener?.remove()
    }

}