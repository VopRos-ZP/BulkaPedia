package vopros.bulkapedia.ui.components.topbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.theme.LocalTopBarViewModel

@Composable
fun TopBar() {
    val viewModel = LocalTopBarViewModel.current

    val title by viewModel.title.collectAsState()
    val showBack by viewModel.showBack.collectAsState()
    val controller by viewModel.navController.collectAsState()

    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            if (showBack) {
                IconButton(onClick = { controller?.navigateUp() }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = {
                controller?.navigate(Destinations.SETTINGS) { launchSingleTop = true }
            }) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = null
                )
            }
        }
    )
}