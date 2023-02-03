package com.bulkapedia.compose.screens.heroes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.events.EventHandler
import com.bulkapedia.compose.data.Firestore
import com.bulkapedia.compose.data.heroes.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class HeroViewState {
    object StateNoItems: HeroViewState()
    object StateLoading: HeroViewState()
    data class StateData(val hero: Hero): HeroViewState()
}

@HiltViewModel
class HeroViewModel @Inject constructor() : ViewModel(), EventHandler<HeroEvent> {

    private val _heroLiveData: MutableLiveData<HeroViewState> = MutableLiveData(HeroViewState.StateLoading)
    val heroLiveData: LiveData<HeroViewState> = _heroLiveData

    var heroId: String = ""

    override fun obtainEvent(event: HeroEvent) {
        when (val currentState = _heroLiveData.value!!) {
            is HeroViewState.StateLoading -> reduce(event, currentState)
            is HeroViewState.StateData -> reduce(event, currentState)
            is HeroViewState.StateNoItems -> reduce(event, currentState)
        }
    }

    private fun reduce(event: HeroEvent, currentState: HeroViewState.StateLoading) {
        when (event) {
            HeroEvent.EnterScreen -> fetchHeroes()
            else -> notImplError(event, currentState)
        }
    }

    private fun reduce(event: HeroEvent, currentState: HeroViewState.StateData) {
        when (event) {
            HeroEvent.EnterScreen -> fetchHeroes()
            HeroEvent.ReloadScreen -> fetchHeroes()
            else -> notImplError(event, currentState)
        }
    }

    private fun reduce(event: HeroEvent, currentState: HeroViewState.StateNoItems) {
        when (event) {
            HeroEvent.ReloadScreen -> fetchHeroes(needsToRefresh = true)
            HeroEvent.EnterScreen -> fetchHeroes()
            else -> notImplError(event, currentState)
        }
    }

    private fun notImplError(event: HeroEvent, currentState: HeroViewState) {
        throw NotImplementedError("Invalid $event for state $currentState")
    }

    private fun fetchHeroes(needsToRefresh: Boolean = false) {
        if (needsToRefresh) {
            _heroLiveData.postValue(HeroViewState.StateLoading)
        }
        viewModelScope.launch {
            val hero = Firestore.Heroes.getHeroes()?.find { it.icon == heroId }
            if (hero == null) {
                _heroLiveData.postValue(HeroViewState.StateNoItems)
            } else {
                _heroLiveData.postValue(HeroViewState.StateData(hero))
            }
        }
    }

}