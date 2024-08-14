package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.hero.Hero

interface HeroRepository {
    val heroes: Flow<List<Hero>>
}