package vopros.bulkapedia.ui.screens.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import vopros.bulkapedia.R
import vopros.bulkapedia.map.GameMap
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.cards.OutlinedCard
import vopros.bulkapedia.ui.screens.Screen
import vopros.bulkapedia.utils.resourceManager

@Composable
fun MapScreen(mapId: String) {
//    Screen<GameMap, MapViewModel>(
//        title = resourceManager.toSource(mapId), showBack = true,
//        fetch = { startIntent(MapViewIntent.Fetch(mapId)) },
//        dispose = { startIntent(MapViewIntent.Dispose) }
//    ) { _, map ->
//        var text by remember { mutableIntStateOf(R.string.show_spawns) }
//        var image by remember { mutableStateOf(map.image) }
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(20.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            OutlinedCard { Image(url = image) }
//            OutlinedButton(onClick = {
//                image = when (image) {
//                    map.image -> map.spawns
//                    else -> map.image
//                }
//                text = when (text) {
//                    R.string.show_spawns -> R.string.hide_spawns
//                    else -> R.string.show_spawns
//                }
//            }) { Text(text) }
//        }
//    }
}