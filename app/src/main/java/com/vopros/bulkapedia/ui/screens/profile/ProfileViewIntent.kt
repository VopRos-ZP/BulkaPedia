package com.vopros.bulkapedia.ui.screens.profile

sealed class ProfileViewIntent {
    data class Start(val userId: String): ProfileViewIntent()
    object Dispose: ProfileViewIntent()
}