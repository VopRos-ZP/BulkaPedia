@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200

@Composable
fun OutlinedTextField(
    text: MutableState<String>,
    label: String,
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
    androidx.compose.material.OutlinedButton(
        enabled = enabled,
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = marginStart, end = marginEnd, top = marginTop, bottom = marginBottom),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, color)
    ) {
        Text(text = text, color = color)
    }
}
