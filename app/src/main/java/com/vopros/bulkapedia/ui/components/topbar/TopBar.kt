package com.vopros.bulkapedia.ui.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.vopros.bulkapedia.ui.theme.LocalTopBarViewModel

@OptIn(ExperimentalMaterial3Api::class)
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
                // navigate to settings
            }) {
                Icon(
                    Icons.Filled.Settings,
                    contentDescription = null
                )
            }
        }
    )
}