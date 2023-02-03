package com.bulkapedia.compose.screens.maps

sealed class MapsEvent {
    object EnterScreen: MapsEvent()
    object LoadingScreen: MapsEvent()
    data class OnMapClick(val mapId: String): MapsEvent()
}