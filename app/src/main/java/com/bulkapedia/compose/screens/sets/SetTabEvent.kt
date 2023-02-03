package com.bulkapedia.compose.screens.sets

sealed class SetTabEvent {
    object Screen: SetTabEvent()
    object Loading: SetTabEvent()
    data class OnErrorEvent(val error: String): SetTabEvent()
    data class OnSetSettingsClick(val setId: String): SetTabEvent()
    data class OnSetLikeClick(val setId: String, val userEmail: String): SetTabEvent()
}