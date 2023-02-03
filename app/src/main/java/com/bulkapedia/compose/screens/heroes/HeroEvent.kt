package com.bulkapedia.compose.screens.heroes

sealed class HeroEvent {
    object EnterScreen: HeroEvent()
    object ReloadScreen: HeroEvent()
    data class OnSetSettingsClick(val setId: String): HeroEvent()
    data class OnSetAuthorClick(val setId: String): HeroEvent()
    data class OnSetCommentsClick(val setId: String): HeroEvent()
}
