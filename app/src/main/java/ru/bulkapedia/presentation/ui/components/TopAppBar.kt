package ru.bulkapedia.presentation.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun Toolbar(
    text: String,
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackTopAppBar(
    text: String,
    onClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = text) },
        navigationIcon = {
            IconButton(onClick = onClick) {
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
