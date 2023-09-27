package vopros.bulkapedia.ui.screens

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import vopros.bulkapedia.R
import vopros.bulkapedia.ui.components.CenterBox
import vopros.bulkapedia.ui.components.ScreenView
import vopros.bulkapedia.ui.view.IntentViewModel
import vopros.bulkapedia.ui.view.ViewState

@Composable
fun Loading() {
    CenterBox { CircularProgressIndicator() }
}

@Composable
fun Error(message: String, onClose: () -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        text = { Text(text = message, color = Color.Red) },
        confirmButton = {
            Button(onClick = onClose) {
                vopros.bulkapedia.ui.components.Text(R.string.ok)
            }
        }
    )
}

@Composable
inline fun <T, reified VM: IntentViewModel<*>> Screen(
    title: Int,
    showBack: Boolean = false,
    crossinline fetch: VM.() -> Unit = {},
    crossinline dispose: VM.() -> Unit = {},
    crossinline content: @Composable (VM, T) -> Unit
) {
    Screen(title = stringResource(title), showBack, fetch, dispose, content)
}

@Suppress("UNCHECKED_CAST")
@Composable
inline fun <T, reified VM: IntentViewModel<*>> Screen(
    title: String,
    showBack: Boolean = false,
    crossinline fetch: VM.() -> Unit = {},
    crossinline dispose: VM.() -> Unit = {},
    crossinline content: @Composable (VM, T) -> Unit
) {
    ScreenView(title = title, showBack = showBack) {
        val viewModel: VM = hiltViewModel()
        val viewState = viewModel.state.collectAsState()
        when (val state = viewState.value) {
            is ViewState.Loading -> Loading()
            is ViewState.Error -> Error(state.message) { viewModel.fetch() }
            else -> {
                val data = (state as ViewState.Success<T>).data
                content(viewModel, data)
            }
        }
        DisposableEffect(null) {
            viewModel.fetch()
            onDispose { viewModel.dispose() }
        }
    }
}