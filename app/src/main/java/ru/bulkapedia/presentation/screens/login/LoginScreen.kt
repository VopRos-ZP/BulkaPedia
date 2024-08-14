package ru.bulkapedia.presentation.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.bulkapedia.R
import ru.bulkapedia.presentation.components.textfield.PasswordTextField
import ru.bulkapedia.presentation.components.topbar.BackTopBar
import ru.bulkapedia.presentation.components.topbar.TopBar
import ru.bulkapedia.presentation.mvi.observeAsState
import ru.bulkapedia.presentation.navigation.Screen

@Composable
fun LoginScreen(
    onNavigate: (Screen) -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state by viewModel.viewStates().observeAsState()
    val viewAction = viewModel.viewActions().observeAsState()

    viewAction.value?.let { action ->
        when (action) {
            is LoginViewAction.IsLogged -> {}
            is LoginViewAction.ShowError -> viewModel.intent(LoginViewIntent.OnErrorChange(stringResource(id = action.text)))
        }
    }

    Scaffold(
        topBar = { TopBar(text = R.string.login) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = state.email,
                        onValueChange = { viewModel.intent(LoginViewIntent.OnEmailChange(it)) },
                        label = { Text(text = stringResource(id = R.string.hint_login)) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                        singleLine = true
                    )
                    PasswordTextField(
                        value = state.password,
                        onValueChange = { viewModel.intent(LoginViewIntent.OnPasswordChange(it)) },
                        visibility = state.isShowPassword,
                        onVisibilityChange = { viewModel.intent(LoginViewIntent.OnVisibilityChange) },
                        label = R.string.hint_password
                    )
                }
                Spacer(modifier = Modifier)
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { viewModel.intent(LoginViewIntent.OnLoginClick) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.login_action))
                    }
                    ClickableText(
                        onClick = { onNavigate(Screen.Registration) },
                        text = stringResource(id = R.string.registration).let { buildAnnotatedString { append(it)} }
                    )
                    ClickableText(
                        onClick = { onNavigate(Screen.ForgotPassword) },
                        text = stringResource(id = R.string.forgot_password).let { buildAnnotatedString { append(it)} }
                    )
                }
                Spacer(modifier = Modifier)
            }

            if (state.error != null) {
                AlertDialog(
                    onDismissRequest = { viewModel.intent(LoginViewIntent.OnErrorChange()) },
                    text = { Text(text = state.error!!) },
                    confirmButton = {
                        TextButton(onClick = { viewModel.intent(LoginViewIntent.OnErrorChange()) }) {
                            Text(text = stringResource(id = R.string.ok))
                        }
                    }
                )
            }
        }
    }
}

@Preview(locale = "ru")
@Composable
fun LoginScreenContent() {
    Scaffold(
        topBar = { BackTopBar({}, R.string.login) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = {},
                        label = { Text(text = stringResource(id = R.string.hint_login)) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                    )
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "asdf",
                        onValueChange = {},
                        trailingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Default.Visibility, contentDescription = null)
                            }
                        },
                        visualTransformation = PasswordVisualTransformation(),
                        label = { Text(text = stringResource(id = R.string.hint_password)) },
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                    )
                }
                Spacer(modifier = Modifier)
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {  },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.login_action))
                    }
                    ClickableText(
                        onClick = {  },
                        text = stringResource(id = R.string.registration).let { buildAnnotatedString { append(it)} }
                    )
                    ClickableText(
                        onClick = {  },
                        text = stringResource(id = R.string.forgot_password).let { buildAnnotatedString { append(it)} }
                    )
                }
                Spacer(modifier = Modifier)
            }
        }
    }
}
