package ru.bulkapedia.presentation.components.textfield

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    visibility: Boolean,
    onVisibilityChange: () -> Unit,
    @StringRes label: Int? = null,
    keyboardType: KeyboardType = KeyboardType.Password
) {
    val (imageVector, visualTransformation) = when (visibility) {
        true -> Icons.Default.Visibility to VisualTransformation.None
        else -> Icons.Default.VisibilityOff to PasswordVisualTransformation()
    }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            IconButton(onClick = onVisibilityChange) {
                Icon(imageVector, contentDescription = null)
            }
        },
        visualTransformation = visualTransformation,
        label = { label?.let { Text(text = stringResource(id = it)) } },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        singleLine = true
    )
}