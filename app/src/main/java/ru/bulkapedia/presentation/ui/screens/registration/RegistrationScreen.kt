package ru.bulkapedia.presentation.ui.screens.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.bulkapedia.R
import ru.bulkapedia.presentation.ui.alert.WithAlert
import ru.bulkapedia.presentation.ui.screens.registration.component.RegistrationComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Регистрация") },
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
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            WithAlert(message = state.error, onClose = viewModel::closeError)
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                OutlinedTextField(
                    value = state.email,
                    onValueChange = viewModel::emailChanged,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = { Text(text = "Введите почту") }
                )
                OutlinedTextField(
                    value = state.password,
                    onValueChange = viewModel::passwordChanged,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = { Text(text = "Введите пароль") },
                    visualTransformation = if (state.isShowPassword) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = when (state.isShowPassword) {
                            true -> Icons.Default.VisibilityOff
                            else -> Icons.Default.Visibility
                        }
                        IconButton(onClick = viewModel::toggleShowPassword) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    }
                )
                OutlinedTextField(
                    value = state.confirmPassword,
                    onValueChange = viewModel::confirmPasswordChanged,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = { Text(text = "Повторите пароль") },
                    visualTransformation = if (state.isShowConfirmPassword) VisualTransformation.None
                    else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = when (state.isShowConfirmPassword) {
                            true -> Icons.Default.VisibilityOff
                            else -> Icons.Default.Visibility
                        }
                        IconButton(onClick = viewModel::toggleShowConfirmPassword) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    }
                )
                OutlinedTextField(
                    value = state.nickname,
                    onValueChange = viewModel::nicknameChanged,
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = { Text(text = "Введите игровой ник") }
                )
                Button(onClick = { viewModel.registrationClick {} }) {
                    Text(text = stringResource(id = R.string.registration))
                }
            }
        }
    }
}