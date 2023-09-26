package com.vopros.bulkapedia.ui.screens.map

sealed class MapViewIntent {
    data class Fetch(val mapId: String): MapViewIntent()
    object Dispose: MapViewIntent()
}