package vopros.bulkapedia.ui.screens.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.Loading
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.cards.Card
import vopros.bulkapedia.utils.resourceManager

@Composable
fun MapScreen(mapId: String, viewModel: MapViewModel = hiltViewModel()) {
    val map by viewModel.map.collectAsState()
    ScreenView(
        title = resourceManager.toSource(map?.id),
        showBack = true,
        viewModel = viewModel
    ) {
        when (val m = map) {
            null -> Loading()
            else -> {
                val mapIconState = remember { mutableStateOf(m.image) }
                val toggleTextState = remember { mutableIntStateOf(R.string.show_spawns) }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) { Image(url = mapIconState.value) }
                    OutlinedButton(onClick = {
                        mapIconState.value = if (mapIconState.value == m.image) m.spawns else m.image
                        toggleTextState.intValue = if (toggleTextState.intValue == R.string.show_spawns) R.string.hide_spawns
                        else R.string.show_spawns
                    }) { Text(toggleTextState.intValue) }
                }
            }
        }
    }
    DisposableEffect(mapId) {
        viewModel.fetch(mapId)
        onDispose { viewModel.dispose() }
    }
}