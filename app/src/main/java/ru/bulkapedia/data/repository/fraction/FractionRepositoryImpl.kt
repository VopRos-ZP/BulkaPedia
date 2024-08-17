package ru.bulkapedia.data.repository.fraction

import ru.bulkapedia.data.room.heroes.FractionDto
import ru.bulkapedia.data.room.heroes.HeroDatabase
import ru.bulkapedia.domain.repository.FractionRepository

class FractionRepositoryImpl(
    private val heroDatabase: HeroDatabase
) : FractionRepository {

    override suspend fun upsert(fractionDto: FractionDto) {
        heroDatabase.dao.upsertFraction(fractionDto)
    }

}