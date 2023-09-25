package com.vopros.bulkapedia.ui.components.topbar

import androidx.navigation.NavController

sealed class TopBarIntent {
    data class Update(
        val title: String,
        val showBack: Boolean,
        val controller: NavController
    ): TopBarIntent()
}