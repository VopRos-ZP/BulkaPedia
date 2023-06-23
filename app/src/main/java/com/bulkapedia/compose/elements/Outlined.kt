@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bulkapedia.R
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.clickable

@Composable
fun OutlinedTextField(
    text: MutableState<String>,
    label: String,
    readOnly: Boolean = false,
    placeholder: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    shape: Shape = MaterialTheme.shapes.small
) {
    androidx.compose.material.OutlinedTextField(
        modifier = modifier,
        label = { Text(text = label) },
        placeholder = placeholder,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            cursorColor = Teal,
            focusedBorderColor = Teal200,
            unfocusedBorderColor = Teal,
            focusedLabelColor = Teal200,
            unfocusedLabelColor = Teal,
            placeholderColor = Teal
        ),
        readOnly = readOnly,
        shape = shape,
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        singleLine = true,
        value = text.value,
        onValueChange = { text.value = it }
    )
}

@Composable
fun OutlinedButton(
    text: String,
    marginTop: Dp = 10.dp,
    marginBottom: Dp = 10.dp,
    marginStart: Dp = 20.dp,
    marginEnd: Dp = 20.dp,
    color: Color = Teal200,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    ModOutlinedButton(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = marginStart, end = marginEnd, top = marginTop, bottom = marginBottom),
        color = color,
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
fun InRowOutlinedButton(
    text: String,
    marginTop: Dp = 10.dp,
    marginBottom: Dp = 10.dp,
    marginStart: Dp = 0.dp,
    marginEnd: Dp = 0.dp,
    color: Color = Teal200,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    ModOutlinedButton(
        text = text,
        modifier = Modifier
            .padding(start = marginStart, end = marginEnd, top = marginTop, bottom = marginBottom),
        color = color,
        enabled = enabled,
        onClick = onClick
    )
}

@Composable
private fun ModOutlinedButton(
    text: String,
    modifier: Modifier,
    color: Color = Teal200,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    androidx.compose.material.OutlinedButton(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, color)
    ) {
        Text(text = text, color = color)
    }
}

@Composable
fun OutlinedPasswordField(password: MutableState<String>) {
    var visiblePassword by remember { mutableStateOf(false) }
    OutlinedTextField(
        text = password,
        label = stringResource(id = R.string.hint_password),
        modifier = Modifier.fillMaxWidth(),
        trailingIcon = {
            val icon = if (visiblePassword) R.drawable.password_visibility_off
            else R.drawable.password_visibility
            Image(painter = painterResource(id = icon),
                colorFilter = ColorFilter.tint(Teal),
                contentDescription = "password_visibility",
                modifier = Modifier.clickable { visiblePassword = visiblePassword.not() }
            )
        },
        visualTransformation = if (visiblePassword) VisualTransformation.None
        else PasswordVisualTransformation(),
    )
}