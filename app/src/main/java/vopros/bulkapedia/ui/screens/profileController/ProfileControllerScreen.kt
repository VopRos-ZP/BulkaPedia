package vopros.bulkapedia.ui.screens.profileController

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.theme.LocalNavController

@Composable
fun ProfileControllerScreen(viewModel: ProfileControllerViewModel = hiltViewModel()) {
    val controller = LocalNavController.current
    val config by viewModel.config.collectAsState()
    LaunchedEffect(config) {
        when (config.second) {
            true -> controller.navigate("${Destinations.PROFILE}/${config.first}")
            else -> controller.navigate(Destinations.LOGIN)
        }
    }
}