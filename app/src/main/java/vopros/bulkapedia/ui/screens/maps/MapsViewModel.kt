package vopros.bulkapedia.ui.screens.maps

import vopros.bulkapedia.map.MapRepository
import vopros.bulkapedia.ui.view.IntentViewModel
import vopros.bulkapedia.ui.view.Reducer
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val mapRepository: MapRepository
): IntentViewModel<MapsViewIntent>() {

    override var reducer: Reducer<MapsViewIntent> = Reducer { intent, _ ->
        when (intent) {
            is MapsViewIntent.Start -> fetchMaps()
        }
    }

    private suspend fun fetchMaps() {
        success(mapRepository.fetchAll())
    }

}