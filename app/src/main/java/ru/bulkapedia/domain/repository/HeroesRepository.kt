package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.Hero

interface HeroesRepository {
    val heroes: Flow<List<Hero>>
}