package com.vopros.bulkapedia.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextField(
    state: MutableState<String>,
    label: Int,
    error: Int? = null,
    isError: Boolean = false
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        label = { Text(label) },
        supportingText = { error?.let { if (isError) Text(it) } },
        isError = isError
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    state: MutableState<String>,
    label: Int
) {
    var isVisible by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = state.value,
        onValueChange = { state.value = it },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        trailingIcon = {
             IconToggleButton(checked = isVisible, onCheckedChange = { isVisible = it }) {
                 Icon(
                     if (isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                     contentDescription = null
                 )
             }
        },
        label = { Text(label) },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation('*')
    )
}