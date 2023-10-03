package vopros.bulkapedia.ui.components.topbar

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.IconButton
import vopros.bulkapedia.ui.components.Text
import vopros.bulkapedia.ui.navigation.Destinations
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.ui.theme.BulkapediaTheme
import vopros.bulkapedia.ui.theme.LocalTopBarViewModel

@Composable
fun TopBar() {
    val viewModel = LocalTopBarViewModel.current

    val title by viewModel.title.collectAsState()
    val showBack by viewModel.showBack.collectAsState()
    val controller by viewModel.navController.collectAsState()
    TopBar(title = title, showBack = showBack, controller = controller)
}

@Preview(showBackground = true)
@Composable
fun TopBar_Preview() {
    BulkapediaTheme {
        TopBar(
            title = stringResource(R.string.alice),
            showBack = true,
            controller = null
        )
    }
}

@Composable
fun TopBar(title: String, showBack: Boolean, controller: NavController?) {
    TopAppBar(
        backgroundColor = BulkaPediaTheme.colors.primary,
        title = { Text(title = title) },
        navigationIcon = {
            if (showBack) {
                IconButton(
                    onClick = { controller?.navigateUp() },
                    icon = Icons.Filled.ArrowBack
                )
            }
        },
        actions = {
            IconButton(onClick = {
                controller?.navigate(Destinations.SETTINGS) { launchSingleTop = true }
            }, icon = Icons.Filled.Settings)
        }
    )
}