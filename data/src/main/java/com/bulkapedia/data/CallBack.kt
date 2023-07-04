package com.bulkapedia.data

data class CallBack<S>(
    val onSuccess: (S) -> Unit,
    val onError: (String) -> Unit
)