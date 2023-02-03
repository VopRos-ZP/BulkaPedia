package com.bulkapedia.compose.screens.profile

sealed class ProfileEvent {
    data class Loading(val email: String): ProfileEvent()
}


