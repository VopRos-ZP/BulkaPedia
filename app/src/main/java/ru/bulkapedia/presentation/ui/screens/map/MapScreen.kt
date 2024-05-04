package ru.bulkapedia.presentation.ui.screens.map

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import ru.bulkapedia.domain.utils.Utils.resourceManager
import ru.bulkapedia.presentation.ui.alert.WithAlert
import ru.bulkapedia.presentation.ui.components.ScaffoldToolbar

@Composable
fun MapScreen(
    navController: NavController,
    viewModel: MapViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    ScaffoldToolbar(resourceManager.toSource(state.map?.id), navController) {
        WithAlert(message = state.error, viewModel::closeError)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    when (val map = state.map) {
                        null -> CircularProgressIndicator()
                        else -> AsyncImage(
                            model = if (state.isShowSpawns) map.spawns else map.original,
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .toggleable(
                        value = state.isShowSpawns,
                        onValueChange = { viewModel.toggleShowSpawns() }
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val text = if (state.isShowSpawns) "Скрыть" else "Показать"
                    Text(text = "$text точки появления")
                }
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.fetchMap()
    }
}