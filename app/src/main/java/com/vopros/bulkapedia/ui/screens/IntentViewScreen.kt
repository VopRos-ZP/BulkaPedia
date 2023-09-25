package com.vopros.bulkapedia.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.vopros.bulkapedia.R
import com.vopros.bulkapedia.ui.components.topbar.TopBarIntent
import com.vopros.bulkapedia.ui.theme.LocalNavController
import com.vopros.bulkapedia.ui.theme.LocalTopBarViewModel
import com.vopros.bulkapedia.ui.view.IntentViewModel
import com.vopros.bulkapedia.ui.view.ViewState

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun Error(message: String) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        text = { Text(text = message, color = Color.Red) },
        confirmButton = {
            Button(onClick = { /*TODO*/ }) {
                com.vopros.bulkapedia.ui.components.Text(R.string.ok)
            }
        }
    )
}

@Suppress("UNCHECKED_CAST")
@Composable
inline fun <T, reified VM: IntentViewModel<*>> Screen(
    title: Int,
    showBack: Boolean = false,
    crossinline fetch: VM.() -> Unit = {},
    crossinline dispose: VM.() -> Unit = {},
    content: @Composable (VM, T) -> Unit
) {
    LocalTopBarViewModel.current.startIntent(
        TopBarIntent.Update(stringResource(title), showBack, LocalNavController.current)
    )
    val viewModel: VM = hiltViewModel()
    val viewState = viewModel.state.collectAsState()
    when (val state = viewState.value) {
        is ViewState.Loading -> Loading()
        is ViewState.Error -> Error(state.message)
        else -> content(viewModel, (state as ViewState.Success<T>).data)
    }
    DisposableEffect(null) {
        fetch(viewModel)
        onDispose { dispose(viewModel) }
    }
}