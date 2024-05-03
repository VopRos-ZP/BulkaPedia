package ru.bulkapedia.core

data class Callback<T>(
    val onSuccess: (T) -> Unit,
    val onError: (String) -> Unit
)