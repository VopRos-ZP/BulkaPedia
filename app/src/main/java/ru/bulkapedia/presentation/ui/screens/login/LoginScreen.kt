package ru.bulkapedia.presentation.ui.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.outlined.RemoveRedEye
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    Scaffold(
        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Авторизация") }) }
    ) {
        LoginScreenContent(it)
    }
}

@Composable
private fun LoginScreenContent(paddingValues: PaddingValues) {
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
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
                value = emailState.value,
                onValueChange = { emailState.value = it },
                prefix = { Text(text = "@") },
                singleLine = true
            )
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { showPassword = !showPassword }) {
//                        Icon(
//                            if (showPassword) Icons.Filled.RemoveRedEye
//                            else Icons.Filled.,
//                            contentDescription = null
//                        )
                    }
                }
            )
        }
    }
}