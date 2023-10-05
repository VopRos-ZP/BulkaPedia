package vopros.bulkapedia.ui.screens.heroes

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import vopros.bulkapedia.hero.Hero
import vopros.bulkapedia.hero.HeroRepository
import vopros.bulkapedia.ui.components.tags.Tag
import vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val heroRepository: HeroRepository
): ErrViewModel() {

    private val _heroes = MutableStateFlow(emptyList<Hero>())
    val heroes = _heroes.asStateFlow()

    fun fetchHeroes(tag: Tag? = null) {
        coroutine {
            _heroes.emit(emptyList()) // clear LazyVerticalGrid
            _heroes.emit(heroRepository.fetchAll().filter { h -> h.type == tag?.id || tag == null })
        }
    }

}