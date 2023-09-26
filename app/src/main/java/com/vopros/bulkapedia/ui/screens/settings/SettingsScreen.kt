package com.vopros.bulkapedia.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.Text
import com.vopros.bulkapedia.ui.screens.Screen

@Composable
fun SettingsScreen() {
    Screen<Pair<String, Boolean>, SettingsViewModel>(
        title = R.string.settings,
        showBack = true,
        fetch = { startIntent(SettingsViewIntent.Start) }
    ) { viewModel, _ ->
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedButton(onClick = { /* Change email */ }) {
                Text(resource = R.string.change_login)
            }
            OutlinedButton(onClick = { /* Change nickname */ }) {
                Text(resource = R.string.change_nickname)
            }
            OutlinedButton(onClick = {
                viewModel.startIntent(SettingsViewIntent.Logout)
            }) { Text(resource = R.string.logout, color = Color.Red) }
        }
    }
}