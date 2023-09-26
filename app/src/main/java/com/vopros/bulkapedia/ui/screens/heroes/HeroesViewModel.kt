package com.vopros.bulkapedia.ui.screens.heroes

import com.vopros.bulkapedia.hero.HeroRepository
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
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