package ru.bulkapedia.data.models.map

import ru.bulkapedia.domain.model.GameMap
import ru.bulkapedia.domain.repository.MapsRepository

class MapsRepositoryImpl(

) : MapsRepository {

    override suspend fun fetchAll(): List<GameMap> {
        TODO("Not yet implemented")
    }

}