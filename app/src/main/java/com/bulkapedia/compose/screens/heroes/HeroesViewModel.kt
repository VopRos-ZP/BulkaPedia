package com.bulkapedia.compose.screens.heroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulkapedia.compose.data.repos.heroes.Hero
import com.bulkapedia.compose.data.repos.firestore.Repository
import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val heroesRepository: Repository<Hero>
) : ViewModel() {

    private val _heroesFlow: MutableStateFlow<List<Hero>> = MutableStateFlow(emptyList())
    val heroesFlow: StateFlow<List<Hero>> = _heroesFlow

    private var listener: ListenerRegistration? = null

    fun fetchHeroes() {
        listener = heroesRepository.fetchAll {
            viewModelScope.launch { _heroesFlow.emit(it) }
        }
    }

    fun dispose() = listener?.remove()

}