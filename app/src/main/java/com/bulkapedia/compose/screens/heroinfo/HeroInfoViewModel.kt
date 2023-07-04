package com.bulkapedia.compose.screens.heroinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.data.CallBack
import com.bulkapedia.data.Repository
import com.bulkapedia.data.hero_info.HeroInfo
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroInfoViewModel @Inject constructor(
    private val heroInfoRepository: Repository<HeroInfo>
) : ViewModel() {

    private val _heroInfoFlow: MutableStateFlow<HeroInfo?> = MutableStateFlow(null)
    val heroInfoFlow: StateFlow<HeroInfo?> = _heroInfoFlow

    private val _heroesInfoCount: MutableStateFlow<Int> = MutableStateFlow(0)
    val heroesInfoCount: StateFlow<Int> = _heroesInfoCount

    private var listener: ListenerRegistration? = null

    fun listenHeroInfo(id: String) {
        listener = heroInfoRepository.fetchAll(CallBack({ heroInfos ->
            viewModelScope.launch {
                _heroInfoFlow.emit(heroInfos.find { it.heroInfoId == id })
                _heroesInfoCount.emit(heroInfos.size)
            }
        }) {})
    }

    fun removeListener() {
        listener?.remove()
    }

}
