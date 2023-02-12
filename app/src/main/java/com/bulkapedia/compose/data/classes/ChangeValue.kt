package com.bulkapedia.compose.data.classes

import androidx.compose.runtime.MutableState

data class ChangeValue(
    val show: MutableState<Boolean>,
    val title: MutableState<String>,
    val fieldLabel: MutableState<String>,
    val infoText: MutableState<String>,
    val value: Value,
    val onSave: MutableState<(Value) -> Unit>
)

data class ChangeValues(
    val show: MutableState<Boolean>,
    val title: MutableState<String>,
    val fieldLabels: MutableState<List<String>>,
    val values: MutableState<List<Value>>,
    val infoText: MutableState<String>,
    val onSave: MutableState<(List<Value>) -> Unit>
)


sealed class Value {
    data class TextValue(val v: MutableState<String>): Value()
    data class SelectValue(val v: MutableState<String>, val items: List<String>): Value()
}
