package vopros.bulkapedia.ui.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import vopros.bulkapedia.ui.theme.BulkaPediaTheme
import vopros.bulkapedia.ui.theme.LocalNavController
import vopros.bulkapedia.ui.theme.LocalTopBarViewModel
import vopros.bulkapedia.ui.view.ErrViewModel

@Composable
inline fun <reified V: ErrViewModel> ScreenView(
    @StringRes title: Int,
    showBack: Boolean = false,
    viewModel: V = hiltViewModel(),
    key: Any? = null,
    crossinline fetch: V.() -> Unit,
    crossinline content: @Composable BoxScope.() -> Unit
) {
    ScreenView(
        title = stringResource(id = title),
        showBack = showBack,
        viewModel = viewModel,
        key = key,
        fetch = fetch,
        content = content
    )
}

@Composable
inline fun <reified V: ErrViewModel> ScreenView(
    title: String, showBack: Boolean = false,
    viewModel: V = hiltViewModel(),
    key: Any? = null,
    crossinline fetch: V.() -> Unit,
    crossinline content: @Composable BoxScope.() -> Unit
) {
    val topBarViewModel = LocalTopBarViewModel.current
    val navController = LocalNavController.current
    topBarViewModel.update(title, showBack, navController)

    val error by viewModel.error.collectAsState()
    CenterBox(modifier = Modifier.background(BulkaPediaTheme.colors.primaryDark)) {
        AnimatedVisibility(visible = error.isNotEmpty()) {
            Error(message = error, onClose = viewModel::closeError)
        }
        content()
    }
    DisposableEffect(key) {
        fetch(viewModel)
        onDispose { viewModel.onDispose() }
    }
}