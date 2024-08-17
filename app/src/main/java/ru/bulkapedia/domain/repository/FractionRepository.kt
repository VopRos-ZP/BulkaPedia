package ru.bulkapedia.domain.repository

import ru.bulkapedia.data.room.heroes.FractionDto

interface FractionRepository {
    suspend fun upsert(fractionDto: FractionDto)
}
