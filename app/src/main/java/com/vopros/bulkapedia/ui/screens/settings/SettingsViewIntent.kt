package com.vopros.bulkapedia.ui.screens.settings

sealed class SettingsViewIntent {
    object Start: SettingsViewIntent()
    object Logout: SettingsViewIntent()
}