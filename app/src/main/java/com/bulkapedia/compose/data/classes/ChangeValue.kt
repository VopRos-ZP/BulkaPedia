package com.bulkapedia.compose.data.classes

import androidx.compose.runtime.MutableState

data class ChangeValue<T>(
    val show: MutableState<Boolean>,
    val title: MutableState<String>,
    val fieldLabel: MutableState<String>,
    val infoText: MutableState<String>,
    val value: MutableState<T>,
    val onSave: MutableState<(T) -> Unit>
)

data class ChangeValues<T>(
    val show: MutableState<Boolean>,
    val title: MutableState<String>,
    val fieldLabels: MutableState<List<String>>,
    val values: MutableState<List<MutableState<T>>>,
    val infoText: MutableState<String>,
    val onSave: MutableState<(List<T>) -> Unit>
)
