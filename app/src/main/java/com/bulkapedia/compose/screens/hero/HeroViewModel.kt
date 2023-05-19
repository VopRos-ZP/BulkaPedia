package com.bulkapedia.compose.screens.hero

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bulkapedia.compose.events.EventHandler
import com.google.firebase.firestore.ListenerRegistration
import com.vopros.domain.Repository
import com.vopros.domain.complete.Complete
import com.vopros.domain.hero.Hero
import com.vopros.domain.set.Set
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(
    private val setsRepo: Repository<Set>,
    private val heroRepo: Repository<Hero>
) : ViewModel(), EventHandler<HeroEvent> {

    private val _heroLiveData: MutableLiveData<HeroViewState> = MutableLiveData(HeroViewState.Loading)
    val heroLiveData: LiveData<HeroViewState> = _heroLiveData

    private var listener: ListenerRegistration? = null
    private var setsListener: ListenerRegistration? = null

    override fun obtainEvent(event: HeroEvent) {
        when (val state = _heroLiveData.value!!) {
            is HeroViewState.Loading -> reduce(event, state)
            is HeroViewState.Active -> reduce(event, state)
            is HeroViewState.Error -> fetchHero()
        }
    }

    private fun reduce(event: HeroEvent, currentState: HeroViewState.Loading) {
        when (event) {
            is HeroEvent.StartScreen -> fetchHero(heroId = event.heroId)
        }
    }

    private fun reduce(event: HeroEvent, currentState: HeroViewState.Active) {
        when (event) {
            is HeroEvent.StartScreen -> fetchHero(heroId = event.heroId)
        }
    }

    private fun reduce(event: HeroEvent, currentState: HeroViewState.Error) {
        when (event) {
            is HeroEvent.StartScreen -> fetchHero(heroId = event.heroId)
        }
    }

    private fun fetchHero(isLoading: Boolean = false, heroId: String = "") {
        if (isLoading) {
            _heroLiveData.postValue(HeroViewState.Loading)
        } else {
            listener = heroRepo.fetchById(heroId, Complete({ hero ->
                setsListener = setsRepo.fetchAll(Complete({ sets ->
                    val top3 = sets
                        .filter { s -> s.hero == hero.heroId }
                        .sortedByDescending { it.likes }
                        .take(3)
                    _heroLiveData.postValue(HeroViewState.Active(hero, top3))
                }) { postError(it) })
            }) { postError(it) })
        }
    }

    private fun postError(message: String) {
        _heroLiveData.postValue(HeroViewState.Error(message))
    }

    fun dispose() {
        setsListener?.remove()
        listener?.remove()
    }

}