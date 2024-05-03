package ru.bulkapedia.presentation.ui.screens.maps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.model.MapMode
import ru.bulkapedia.domain.utils.Utils.resourceManager
import ru.bulkapedia.presentation.ui.alert.WithAlert
import ru.bulkapedia.presentation.ui.screens.maps.component.MapsComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen(component: MapsComponent) {
    val state by component.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Карты") },
                navigationIcon = {
                    IconButton(onClick = component::onNavigationBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            WithAlert(message = state.error, onClose = component::onCloseError)
            Column(modifier = Modifier.fillMaxSize()) {
                val tags = MapMode.entries.toList()
                LazyRow {
                    items(tags) { mode ->
                        val isSelected = state.selectedMode == mode
                        FilterChip(
                            selected = isSelected,
                            onClick = { component.onMapModeSelected(if (isSelected) null else mode) },
                            label = { Text(text = stringResource(resourceManager.toSource(mode.name.lowercase()))) }
                        )
                    }
                }
                val maps = state.selectedMode?.let { mode -> state.maps.filter { m -> m.mode == mode } } ?: state.maps
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(maps) { map ->
                        GameMapCard(map) { component.onGameMapClicked(map) }
                    }
                }
            }
        }
    }
}

@Composable
fun GameMapCard(map: GameMap, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = stringResource(resourceManager.toSource(map.id)))
            AsyncImage(
                model = map.spawns,
                contentDescription = null
            )
        }
    }
}