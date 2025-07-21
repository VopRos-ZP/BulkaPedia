package ru.bulkapedia.presentation.ui.screens.registration

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyOff
import androidx.compose.material.icons.filled.VpnKey
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.bulkapedia.R

@Composable
fun RegistrationScreen(
    viewModel: RegistrationVewModel = koinViewModel(),
) {
    val state by viewModel.collectAsState()
    viewModel.collectSideEffect {
        when (it) {
            is RegistrationSideEffect.OnLoginClick -> {}
            is RegistrationSideEffect.OnSuccessRegistration -> {}
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = state.email,
                onValueChange = { viewModel.onEmailChange(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(
                        onClick = { viewModel.onTogglePasswordClick() }
                    ) {
                        val imageVector = when (state.isShowPassword) {
                            true -> Icons.Default.VpnKey
                            else -> Icons.Default.KeyOff
                        }
                        Icon(
                            imageVector =  imageVector,
                            contentDescription = imageVector.name
                        )
                    }
                },
                visualTransformation = when (state.isShowPassword) {
                    true -> PasswordVisualTransformation()
                    else -> VisualTransformation.None
                }
            )
            OutlinedTextField(
                value = state.nickname,
                onValueChange = { viewModel.onNicknameChange(it) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { viewModel.onLoginClick() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.login)
                    )
                }
                Button(
                    onClick = { viewModel.onRegistrationClick() },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = stringResource(R.string.registration)
                    )
                }
            }
        }
    }
}