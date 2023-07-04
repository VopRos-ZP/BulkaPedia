package com.bulkapedia.compose.screens.mechanics

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.bulkapedia.compose.navigation.Destinations
import com.bulkapedia.compose.screens.titled.ScreenView
import com.bulkapedia.compose.ui.theme.LocalNavController

@Composable
fun MechanicsScreen(viewModel: MechanicsViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val mechanics by viewModel.mechanicsFlow.collectAsState()
    ScreenView(title = "Механики игры", showBack = true) {
        LazyColumn {
            items(mechanics) { MechanicsItem(it) {
                navController.navigate("${Destinations.MECHANICS}/${it.mechanicId}")
            } }
        }
    }
    // Listener
    DisposableEffect(null) {
        viewModel.listenMechanics()
        onDispose { viewModel.removeListener() }
    }
}
