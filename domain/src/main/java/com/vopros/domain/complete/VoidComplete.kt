package com.vopros.domain.complete

data class VoidComplete(
    val success: () -> Unit,
    val error: (String) -> Unit
)
