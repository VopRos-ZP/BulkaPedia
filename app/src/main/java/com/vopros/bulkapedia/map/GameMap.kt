package com.vopros.bulkapedia.map

import com.vopros.bulkapedia.core.Entity

data class GameMap(
    override val id: String,
    val image: String,
    val spawns: String,
    val mode: String
): Entity(id)
