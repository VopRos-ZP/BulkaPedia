package ru.bulkapedia.data.repository.hero

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bulkapedia.data.room.heroes.HeroDatabase
import ru.bulkapedia.data.room.heroes.HeroDto
import ru.bulkapedia.data.room.heroes.HeroWithFraction
import ru.bulkapedia.data.room.heroes.fromDto
import ru.bulkapedia.domain.repository.HeroRepository

class HeroRepositoryImpl(
    private val heroDatabase: HeroDatabase
) : HeroRepository {

    override fun listenAll(): Flow<List<ru.bulkapedia.domain.model.hero.Hero>> =
        heroDatabase.dao.listenAll()
            .map { it.map(HeroWithFraction::fromDto) }

    override suspend fun upsert(heroDto: HeroDto) {
        heroDatabase.dao.upsertHero(heroDto)
    }

}