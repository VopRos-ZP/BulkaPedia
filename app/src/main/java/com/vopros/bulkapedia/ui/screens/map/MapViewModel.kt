package com.vopros.bulkapedia.ui.screens.map

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ListenerRegistration
import com.vopros.bulkapedia.core.Callback
import com.vopros.bulkapedia.map.MapRepository
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.Reducer
import com.vopros.bulkapedia.ui.view.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapRepository: MapRepository
): IntentViewModel<MapViewIntent>() {

    private var listener: ListenerRegistration? = null

    override var reducer: Reducer<MapViewIntent> = Reducer { intent, _ ->
        when (intent) {
            is MapViewIntent.Fetch -> fetch(intent.mapId)
            is MapViewIntent.Dispose -> dispose()
        }
    }

    private suspend fun fetch(mapId: String) {
        listener = mapRepository.listenOne(mapId, Callback(this::onError) {
            viewModelScope.launch { innerState.emit(ViewState.Success(it)) }
        })
    }

    private fun dispose() {
        listener?.remove()
    }

}