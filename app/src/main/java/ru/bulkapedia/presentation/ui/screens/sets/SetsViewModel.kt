package ru.bulkapedia.presentation.ui.screens.sets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bulkapedia.domain.model.Hero
import ru.bulkapedia.domain.model.HeroType
import ru.bulkapedia.domain.repository.HeroesRepository
import javax.inject.Inject

@HiltViewModel
class SetsViewModel @Inject constructor(
    private val heroesRepository: HeroesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(Sets.State())
    val state = _state.asStateFlow()

    fun fetchHeroes() {
        viewModelScope.launch {
            heroesRepository.heroes.collect {
                _state.value = state.value.copy(heroes = it.filter(Hero::active))
            }
        }
    }

    fun filterType(type: HeroType?) {
        _state.value = state.value.copy(selectedHeroType = type)
    }

    fun closeError() {
        _state.value = state.value.copy(error = null)
    }

}