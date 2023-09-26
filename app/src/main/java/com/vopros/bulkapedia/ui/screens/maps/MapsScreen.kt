package com.vopros.bulkapedia.ui.screens.maps

import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.map.GameMap
import com.vopros.bulkapedia.ui.components.HCenterBox
import com.vopros.bulkapedia.ui.components.Image
import com.vopros.bulkapedia.ui.components.TagsWithRecycler
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.components.tags.mapsTags
import com.vopros.bulkapedia.ui.navigation.Destinations
import com.vopros.bulkapedia.ui.screens.Screen
import com.vopros.bulkapedia.ui.theme.LocalNavController
import com.vopros.bulkapedia.utils.resourceManager

@Composable
fun MapsScreen() {
    Screen<List<GameMap>, MapsViewModel>(
        R.string.select_map, true,
        { startIntent(MapsViewIntent.Start) }
    ) { _, maps ->
        TagsWithRecycler(mapsTags(), maps, { tag, map ->
            tag?.id == map.mode || tag == null || tag.id.isEmpty()
        }) { MapCard(it) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
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