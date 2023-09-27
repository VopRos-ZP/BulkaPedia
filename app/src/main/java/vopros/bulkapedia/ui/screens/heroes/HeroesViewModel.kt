package vopros.bulkapedia.ui.screens.heroes

import vopros.bulkapedia.hero.HeroRepository
import vopros.bulkapedia.ui.view.IntentViewModel
import vopros.bulkapedia.ui.view.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroesViewModel @Inject constructor(
    private val heroRepository: HeroRepository
) : IntentViewModel<HeroesViewIntent>() {

    override var reducer: Reducer<HeroesViewIntent> = Reducer { intent, _ ->
        when (intent) {
            is HeroesViewIntent.Start -> fetchHeroes()
        }
    }

    private suspend fun fetchHeroes() {
        success(heroRepository.fetchAll())
    }

}