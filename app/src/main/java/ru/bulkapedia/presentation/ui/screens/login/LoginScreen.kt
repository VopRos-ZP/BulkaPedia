package ru.bulkapedia.presentation.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.bulkapedia.presentation.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Авторизация") }) }
    ) {
        LoginScreenContent(it, navController)
    }
}

@Composable
private fun LoginScreenContent(
    paddingValues: PaddingValues,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.emailChanged(it) },
                prefix = { Text(text = "@") },
                singleLine = true
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.passwordChanged(it) },
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { viewModel.toggleShowPassword() }) {
                        Icon(
                            if (state.isShowPassword) Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (state.isShowPassword) VisualTransformation.None
                else PasswordVisualTransformation()
            )
            Button(onClick = { viewModel.loginClick { navController.navigate("profile") } }) {
                Text(text = "Войти")
            }
            Button(onClick = { navController.navigate(Screen.Registration.route) }) {
                Text(text = "Регистрация")
            }
        }
    }
}