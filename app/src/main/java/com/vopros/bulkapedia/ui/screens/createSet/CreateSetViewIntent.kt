package com.vopros.bulkapedia.ui.screens.createSet

sealed class CreateSetViewIntent {
    data class Start(
        val heroId: String,
        val setId: String? = null
    ): CreateSetViewIntent()
}