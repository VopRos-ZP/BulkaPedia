package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.GameMap

interface MapsRepository {
    val maps: Flow<List<GameMap>>
    val map: (String) -> Flow<GameMap>
}