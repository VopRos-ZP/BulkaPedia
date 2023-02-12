@file:Suppress("FunctionName")

package com.bulkapedia.compose.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.bulkapedia.compose.data.classes.ChangeValue
import com.bulkapedia.compose.data.classes.ChangeValues
import com.bulkapedia.compose.data.classes.Value
import com.bulkapedia.compose.ui.theme.PrimaryDark
import com.bulkapedia.compose.ui.theme.Teal200
import com.bulkapedia.compose.util.CenteredBox
import com.bulkapedia.compose.util.HCenteredBox

@Composable
fun ConfirmDialog(
    state: MutableState<Boolean>,
    title: String,
    text: String,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {/* On touch outside */},
        modifier = Modifier.border(2.dp, Teal200, RoundedCornerShape(20.dp)),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = PrimaryDark,
        title = { Text(text = title, color = Teal200, fontWeight = FontWeight.Bold) },
        text = { Text(text = text, color = Teal200) },
        buttons = {
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                InRowOutlinedButton(text = "Отмена", color = Color.Red) {
                    state.value = false
                }
                InRowOutlinedButton(text = "Удалить", color = Color.Yellow) {
                    state.value = false
                    onConfirm.invoke()
                }
            }
        }
    )
}

@Composable
fun ChangesValueDialog(changeValue: ChangeValues) {
    Dialog(onDismissRequest = {/* On touch outside */}) {
        Column (
            modifier = Modifier
                .background(PrimaryDark, RoundedCornerShape(20.dp))
                .border(2.dp, Teal200, RoundedCornerShape(20.dp))
                .padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            HCenteredBox { Text(text = changeValue.title.value, color = Teal200, fontSize = 18.sp) }
            changeValue.values.value.mapIndexed { i, value ->
                OutlinedTextField(
                    (value as Value.TextValue).v, changeValue.fieldLabels.value[i],
                    shape = RoundedCornerShape(10.dp)
                )
            }
            if (changeValue.infoText.value.isNotEmpty()) {
                Box(modifier = Modifier.padding(top = 20.dp, bottom = 5.dp)) { InfoBox(changeValue.infoText.value) }
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InRowOutlinedButton(text = "Отмена", color = Color.Red) {
                    changeValue.show.value = false
                }
                InRowOutlinedButton(text = "Сохранить", color = Color.Green) {
                    changeValue.show.value = false
                    changeValue.onSave.value.invoke(changeValue.values.value.map { (it as Value.TextValue) })
                }
            }
        }
    }
}

@Composable
fun ChangeValueDialog(
    changeValue: ChangeValue
) {
    Dialog(onDismissRequest = {/* On touch outside */}) {
        Column (
            modifier = Modifier
                .background(PrimaryDark, RoundedCornerShape(20.dp))
                .border(2.dp, Teal200, RoundedCornerShape(20.dp))
                .padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            HCenteredBox { Text(text = changeValue.title.value, color = Teal200, fontSize = 18.sp) }
            OutlinedTextField(
                (changeValue.value as Value.TextValue).v, changeValue.fieldLabel.value,
                shape = RoundedCornerShape(10.dp)
            )
            Box(modifier = Modifier.padding(top = 20.dp, bottom = 5.dp)) { InfoBox(changeValue.infoText.value) }
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InRowOutlinedButton(text = "Отмена", color = Color.Red) {
                    changeValue.show.value = false
                }
                InRowOutlinedButton(text = "Сохранить", color = Color.Green) {
                    changeValue.show.value = false
                    changeValue.onSave.value.invoke(changeValue.value)
                }
            }
        }
    }
}

@Composable
fun ScreenWithChangeDialog(
    changeValue: ChangeValue,
    content: @Composable () -> Unit
) {
    CenteredBox {
        content.invoke()
        if (changeValue.show.value) { ChangeValueDialog(changeValue) }
    }
}

@Composable
fun ScreenWithChangesDialog(
    changeValue: ChangeValues,
    content: @Composable () -> Unit
) {
    CenteredBox {
        content.invoke()
        if (changeValue.show.value) { ChangesValueDialog(changeValue) }
    }
}
