package com.vopros.domain.complete

data class Complete<S>(
    val success: (S) -> Unit,
    val error: (String) -> Unit
)