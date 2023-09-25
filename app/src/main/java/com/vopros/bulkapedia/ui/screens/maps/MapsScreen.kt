package com.vopros.bulkapedia.ui.screens.maps

import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.map.GameMap
import com.vopros.bulkapedia.ui.components.Image
import com.vopros.bulkapedia.ui.components.TagsWithRecycler
import com.vopros.bulkapedia.ui.components.tags.mapsTags
import com.vopros.bulkapedia.ui.screens.Screen
import com.vopros.bulkapedia.ui.screens.heroes.HeroCard

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
    Card(
        onClick = { /*TODO*/ },
    ) {
        Text(text = map.id) // get name by id
        Image(url = map.image)
    }
}