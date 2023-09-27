package vopros.bulkapedia.ui.screens.map

import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ListenerRegistration
import vopros.bulkapedia.core.Callback
import vopros.bulkapedia.map.MapRepository
import vopros.bulkapedia.ui.view.IntentViewModel
import vopros.bulkapedia.ui.view.Reducer
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

    private fun fetch(mapId: String) {
        listener = mapRepository.listenOne(mapId, Callback(this::error) {
            viewModelScope.launch { success(it) }
        })
    }

    private fun dispose() {
        listener?.remove()
    }

}