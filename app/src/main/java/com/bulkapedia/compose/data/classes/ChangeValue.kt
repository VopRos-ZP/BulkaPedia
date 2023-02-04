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