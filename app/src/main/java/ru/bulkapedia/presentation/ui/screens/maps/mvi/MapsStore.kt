package ru.bulkapedia.presentation.ui.screens.maps.mvi

import com.arkivanov.mvikotlin.core.store.Store

interface MapsStore : Store<Maps.Intent, Maps.State, Maps.Label>
