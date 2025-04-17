package ru.vopros.bulkapedia.common.domain

data class GameMap(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val spawnsUrl: String,
    val gameMode: GameMode,
)
