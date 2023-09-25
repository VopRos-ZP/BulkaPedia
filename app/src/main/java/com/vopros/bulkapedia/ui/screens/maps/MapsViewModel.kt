package com.vopros.bulkapedia.ui.screens.maps

import com.vopros.bulkapedia.map.MapRepository
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
import com.vopros.bulkapedia.ui.view.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val mapRepository: MapRepository
): IntentViewModel<MapsViewIntent>() {

    override var reducer: Reducer<MapsViewIntent> = Reducer { intent, state ->
        when (intent) {
            is MapsViewIntent.Start -> fetchMaps()
        }
    }

    private suspend fun fetchMaps(): ViewState {
        return ViewState.Success(mapRepository.fetchAll())
    }

}