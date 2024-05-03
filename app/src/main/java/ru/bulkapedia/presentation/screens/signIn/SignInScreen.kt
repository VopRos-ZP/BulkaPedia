package ru.bulkapedia.presentation.screens.signIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import ru.bulkapedia.presentation.screens.signIn.mvi.SignIn
import ru.bulkapedia.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel<SignInViewModel>()
) {
    val state by viewModel.stateFlow.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.message != null) {

        }
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.accept(SignIn.Intent.EmailChanged(it)) }
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.accept(SignIn.Intent.PasswordChanged(it)) }
            )
            Button(onClick = { viewModel.accept(SignIn.Intent.LoginClick) }) {
                Text(text = stringResource(id = R.string.login))
            }
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.labels.collect {
            when (it) {
                is SignIn.Label.Navigate -> {}//navigator.navigate(it.direction)
                is SignIn.Label.Snackbar -> {}
            }
        }
    }
    DisposableEffect(key1 = Unit) {
        viewModel.init()
        onDispose { viewModel.dispose() }
    }
}