package ru.bulkapedia.presentation.ui.screens.maps

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.model.MapMode
import ru.bulkapedia.domain.utils.Utils.resourceManager
import ru.bulkapedia.presentation.ui.alert.WithAlert
import ru.bulkapedia.presentation.ui.components.ScaffoldToolbar
import ru.bulkapedia.presentation.ui.navigation.Screen

@Composable
fun MapsScreen(
    navController: NavController,
    viewModel: MapsViewModel =  hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ScaffoldToolbar(text = "Карты", navController) {
        WithAlert(message = state.error, onClose = viewModel::closeError)
        Column(modifier = Modifier.fillMaxSize()) {
            val tags = MapMode.entries.toList()
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                items(tags) { mode ->
                    val isSelected = state.selectedMode == mode
                    FilterChip(
                        selected = isSelected,
                        onClick = { viewModel.filterMaps(if (isSelected) null else mode) },
                        label = { Text(text = stringResource(resourceManager.toSource(mode.name.lowercase()))) }
                    )
                }
            }
            val maps = state.selectedMode?.let { mode -> state.maps.filter { m -> m.mode == mode } } ?: state.maps
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                items(maps) { map ->
                    GameMapCard(map) { navController.navigate(Screen.Map(map.id).route) }
                }
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchMaps()
    }
}

@Composable
fun GameMapCard(map: GameMap, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(text = stringResource(resourceManager.toSource(map.id)))
            AsyncImage(
                model = map.original,
                contentDescription = null
            )
        }
    }
}
