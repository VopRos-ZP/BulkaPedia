package com.vopros.bulkapedia.core

data class Callback<T>(
    val onError: (String) -> Unit,
    val onSuccess: (T) -> Unit
)