package vopros.bulkapedia.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.components.button.OutlinedButton

@Destination
@Composable
fun SettingsScreen(navigator: DestinationsNavigator, viewModel: SettingsViewModel) {
    ScreenView(
        title = R.string.settings,
        showBack = true,
        viewModel = viewModel,
        fetch = { init() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(
                onClick = { /* Change email */ },
                text = R.string.change_login
            )
            OutlinedButton(
                onClick = { /* Change nickname */ },
                text = R.string.change_nickname
            )
            OutlinedButton(
                onClick = { viewModel.logout() },
                text = R.string.logout,
                color = Color.Red
            )
        }
    }
}