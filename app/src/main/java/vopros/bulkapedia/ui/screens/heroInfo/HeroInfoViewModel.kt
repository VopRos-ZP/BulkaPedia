package vopros.bulkapedia.ui.screens.heroInfo

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import vopros.bulkapedia.core.Callback
import vopros.bulkapedia.hero.Hero
import vopros.bulkapedia.hero.HeroRepository
import vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class HeroInfoViewModel @Inject constructor(
    private val heroRepository: HeroRepository,
): ErrViewModel() {

    private val _hero = MutableStateFlow<Hero?>(null)
    val hero = _hero.asStateFlow()

    fun fetchHero(heroId: String) {
        listeners["hero"] = heroRepository.listenOne(heroId, Callback(::error) {
            coroutine { _hero.emit(it) }
        })
    }

}