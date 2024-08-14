package ru.bulkapedia.presentation.screens.registration

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import org.koin.androidx.compose.koinViewModel
import ru.bulkapedia.presentation.mvi.observeAsState

@Composable
fun RegistrationScreen(
    onClick: () -> Unit,
    viewModel: RegistrationViewModel = koinViewModel()
) {
    val state by viewModel.viewStates().observeAsState()
    val action = viewModel.viewActions().observeAsState()

    action.value?.let {
        when (it) {
            is RegistrationViewAction.IsRegistered -> onClick()
            is RegistrationViewAction.ShowError -> viewModel.intent(
                RegistrationViewIntent.OnErrorChange(stringResource(id = it.text))
            )
        }
    }

    // UI
    
}