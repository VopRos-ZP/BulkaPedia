package vopros.bulkapedia.ui.screens.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.Loading
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.cards.Card
import vopros.bulkapedia.ui.components.checkBox.CheckBox
import vopros.bulkapedia.utils.resourceManager

@Destination
@Composable
fun MapScreen(viewModel: MapViewModel, mapId: String) {
    val map by viewModel.map.collectAsState()
    ScreenView(
        title = resourceManager.toSource(map?.id),
        showBack = true,
        viewModel = viewModel,
        fetch = { fetch(mapId) }
    ) {
        when (val m = map) {
            null -> Loading()
            else -> {
                val showSpawns = remember { mutableStateOf(false) }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) { Image(url = if (showSpawns.value) m.spawns else m.image ) }
                    CheckBox(
                        state = showSpawns,
                        text = if (showSpawns.value) R.string.hide_spawns else R.string.show_spawns
                    )
                }
            }
        }
    }
}