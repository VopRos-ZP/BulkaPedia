package vopros.bulkapedia.ui.screens.createSet

import vopros.bulkapedia.hero.HeroRepository
import vopros.bulkapedia.storage.DataStore
import vopros.bulkapedia.ui.view.IntentViewModel
import vopros.bulkapedia.ui.view.Reducer
import vopros.bulkapedia.user.UserRepository
import vopros.bulkapedia.userSet.SetRepository
import vopros.bulkapedia.userSet.UserSet
import vopros.bulkapedia.userSet.UserSetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class CreateSetViewModel @Inject constructor(
    private val dataStore: DataStore,
    private val setRepository: SetRepository,
    private val userRepository: UserRepository,
    private val heroRepository: HeroRepository,
): ErrViewModel() {

//    override var reducer: Reducer<CreateSetViewIntent> = Reducer { intent, _ ->
//        when (intent) {
//            is CreateSetViewIntent.Start -> fetchSet(intent.heroId, intent.setId)
//        }
//    }
//
//    private suspend fun fetchSet(heroId: String, setId: String?) {
//        dataStore.userId.collect { token ->
//            val user = userRepository.fetchOne(token)
//            val hero = heroRepository.fetchOne(heroId)
//            val set = when (setId) {
//                null -> UserSet("", token, emptyMap(), heroId, emptyList())
//                else -> setRepository.fetchOne(setId)
//            }
//            success(UserSetUseCase(set, user, hero))
//        }
//    }

}