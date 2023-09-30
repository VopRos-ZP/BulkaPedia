package vopros.bulkapedia.ui.screens.maps

import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import vopros.bulkapedia.R
import vopros.bulkapedia.map.GameMap
import vopros.bulkapedia.ui.components.HCenterBox
import vopros.bulkapedia.ui.components.Image
import vopros.bulkapedia.ui.components.TagsWithRecycler
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.components.tags.mapsTags
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.screens.Screen
import vopros.bulkapedia.ui.theme.LocalNavController
import vopros.bulkapedia.utils.resourceManager

@Composable
fun MapsScreen() {
//    Screen<List<GameMap>, MapsViewModel>(
//        R.string.select_map, true,
//        { startIntent(MapsViewIntent.Start) }
//    ) { _, maps ->
//        TagsWithRecycler(mapsTags(), maps, { tag, map ->
//            tag?.id == map.mode || tag == null || tag.id.isEmpty()
//        }) { MapCard(it) }
//    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MapCard(map: GameMap) {
    val controller = LocalNavController.current
    Card(
        onClick = { controller.navigate("${Destinations.MAP}${map.id}") },
    ) {
        HCenterBox {
            Text(resourceManager.toSource(map.id), fontWeight = FontWeight.Bold)
        }
        Image(url = map.image)
    }
}