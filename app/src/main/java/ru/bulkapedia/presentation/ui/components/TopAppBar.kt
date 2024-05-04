package ru.bulkapedia.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Toolbar(
    text: String,
    navController: NavController? = null,
) {
    when (navController) {
        null -> TitleAppBar(text)
        else -> BackTopAppBar(text, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackTopAppBar(
    text: String,
    navController: NavController,
) {
    TopAppBar(
        title = { Text(text = text) },
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleAppBar(text: String) {
    TopAppBar(title = { Text(text = text) })
}
