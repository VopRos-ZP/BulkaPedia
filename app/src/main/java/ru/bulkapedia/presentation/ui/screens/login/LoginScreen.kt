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
import ru.bulkapedia.presentation.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
) {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Авторизация") }) }
    ) {
        LoginScreenContent(it)
    }
}

@Composable
private fun LoginScreenContent(
    paddingValues: PaddingValues
) {

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
                value = "",
                onValueChange = {  },
                prefix = { Text(text = "@") },
                singleLine = true
            )
            OutlinedTextField(
                value = "state.password",
                onValueChange = {  },
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            if (true) Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility,
                            contentDescription = null
                        )
                    }
                },
                visualTransformation = if (true) VisualTransformation.None
                else PasswordVisualTransformation()
            )
            Button(onClick = { }) {
                Text(text = "Войти")
            }
            Button(onClick = {  }) {
                Text(text = "Регистрация")
            }
        }
    }
}