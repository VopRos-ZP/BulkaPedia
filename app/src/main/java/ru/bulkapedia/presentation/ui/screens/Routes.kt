package ru.bulkapedia.presentation.ui.screens

import kotlinx.serialization.Serializable

@Serializable
object Splash

@Serializable
object Main

@Serializable
object App

@Serializable
object GearPrices

@Serializable
object Categories

@Serializable
object Heroes

@Serializable
data class HeroDetails(val id: String)

@Serializable
object Auth

@Serializable
object Login

@Serializable
object Registration
