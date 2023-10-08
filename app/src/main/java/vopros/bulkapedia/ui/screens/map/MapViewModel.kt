package vopros.bulkapedia.ui.screens.map

import com.google.firebase.firestore.ListenerRegistration
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import vopros.bulkapedia.core.Callback
import vopros.bulkapedia.map.GameMap
import vopros.bulkapedia.map.MapRepository
import vopros.bulkapedia.ui.view.ErrViewModel
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val mapRepository: MapRepository
): ErrViewModel() {

    private val _map = MutableStateFlow<GameMap?>(null)
    val map = _map.asStateFlow()

    private var listener: ListenerRegistration? = null

    fun fetch(mapId: String) {
        listener = mapRepository.listenOne(mapId, Callback(::error) {
            coroutine { _map.emit(it) }
        })
    }

    fun dispose() {
        listener?.remove()
    }

}