package ru.bulkapedia.domain.usecase

import ru.bulkapedia.data.room.heroes.HeroDto
import ru.bulkapedia.domain.repository.HeroRepository

class UpsertHeroesUseCase(
    private val heroRepository: HeroRepository
) {

    suspend operator fun invoke(heroes: List<HeroDto>) {
        heroes.forEach { heroRepository.upsert(it) }
    }

}