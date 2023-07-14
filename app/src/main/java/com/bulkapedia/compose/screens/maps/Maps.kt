package com.bulkapedia.compose.screens.maps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import bulkapedia.maps.GameMap
import com.bulkapedia.compose.elements.OutlinedCard
import com.bulkapedia.compose.elements.mapsTags
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.screens.card_recycler.TagsWithRecycler
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.LocalNavController
import com.bulkapedia.compose.ui.theme.Teal200
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun Maps(viewModel: MapsViewModel = hiltViewModel()) {
    val maps by viewModel.mapsFlow.collectAsState()
    val navController = LocalNavController.current
    ScreenView(title = "Выберите карту", showBack = true) {
        TagsWithRecycler(mapsTags(), maps, { tag, map ->
            tag?.text == map.mode.toString() || tag == null
        }) { map -> MapCard(map) {
            navController.navigate("${Destinations.MAPS}/${map.mapId}") { launchSingleTop = true }
        } }
    }
    DisposableEffect(null) {
        viewModel.fetchMaps()
        onDispose { viewModel.dispose() }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MapCard(map: GameMap, onClick: () -> Unit) {
    OutlinedCard(
        shape = RoundedCornerShape(25.dp),
        onClick = onClick
    ) {
        Column (
            modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = map.name, textAlign = TextAlign.Center, color = Teal200)
            GlideImage(
                model = map.image,
                contentDescription = map.name,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
        }
    }
}
