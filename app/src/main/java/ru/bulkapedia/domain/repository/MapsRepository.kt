package ru.bulkapedia.domain.repository

import ru.bulkapedia.domain.model.GameMap

interface MapsRepository {
    suspend fun fetchAll(): List<GameMap>
}