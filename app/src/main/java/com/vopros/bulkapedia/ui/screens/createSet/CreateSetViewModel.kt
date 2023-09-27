package com.vopros.bulkapedia.ui.screens.createSet

import com.vopros.bulkapedia.hero.HeroRepository
import com.vopros.bulkapedia.storage.DataStore
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
import com.vopros.bulkapedia.user.UserRepository
import com.vopros.bulkapedia.userSet.SetRepository
import com.vopros.bulkapedia.userSet.UserSet
import com.vopros.bulkapedia.userSet.UserSetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateSetViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val setRepository: SetRepository,
    private val userRepository: UserRepository,
    private val heroRepository: HeroRepository,
): IntentViewModel<CreateSetViewIntent>() {

    override var reducer: Reducer<CreateSetViewIntent> = Reducer { intent, _ ->
        when (intent) {
            is CreateSetViewIntent.Start -> fetchSet(intent.heroId, intent.setId)
        }
    }

    private suspend fun fetchSet(heroId: String, setId: String?) {
        dataStore.userId.collect { token ->
            val user = userRepository.fetchOne(token)
            val hero = heroRepository.fetchOne(heroId)
            val set = when (setId) {
                null -> UserSet("", token, emptyMap(), heroId, emptyList())
                else -> setRepository.fetchOne(setId)
            }
            success(UserSetUseCase(set, user, hero))
        }
    }

}