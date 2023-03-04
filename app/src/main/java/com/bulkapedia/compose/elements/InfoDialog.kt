@file:Suppress("FunctionName")
package com.bulkapedia.compose.elements

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal
import com.bulkapedia.compose.ui.theme.Teal200

@Composable
fun ScreenWithInfo(
    show: MutableState<Boolean> = mutableStateOf(false),
    content: @Composable (ScreenAction.InfoAction) -> Unit
) {
    val text = remember { mutableStateOf("") }
    val onClick = remember { mutableStateOf({}) }
    val action = ScreenAction.InfoAction(show, text, onClick)
    Box(modifier = Modifier.fillMaxSize()) {
        content.invoke(action)
        AnimatedVisibility(show.value) {
            InfoDialog(action)
        }
    }
}

@Composable
fun InfoDialog(action: ScreenAction.InfoAction) {
    AlertDialog(
        onDismissRequest = {/* On touch outside */},
        shape = RoundedCornerShape(20.dp),
        backgroundColor = PrimaryDark,
        text = { Text(text = action.text.value, color = Teal) },
        title = { Text(text = "Информация", color = Color.Green, fontSize = 18.sp, fontWeight = FontWeight.Bold) },
        modifier = Modifier.border(2.dp, Teal200, RoundedCornerShape(20.dp)),
        confirmButton = {
            InRowOutlinedButton(text = "Ок", marginEnd = 10.dp, color = Color.Green) {
                action.show.value = false
                action.onClose.value.invoke()
            }
        }
    )
}