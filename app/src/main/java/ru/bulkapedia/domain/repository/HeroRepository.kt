package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.data.room.heroes.HeroDto
import ru.bulkapedia.domain.model.hero.Hero

interface HeroRepository {
    fun listenAll(): Flow<List<Hero>>
    suspend fun upsert(heroDto: HeroDto)
}