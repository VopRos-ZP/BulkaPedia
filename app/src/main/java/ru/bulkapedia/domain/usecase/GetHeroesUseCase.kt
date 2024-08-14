package ru.bulkapedia.domain.usecase

import ru.bulkapedia.domain.repository.HeroRepository

class GetHeroesUseCase(
    private val heroRepository: HeroRepository,
) {

    operator fun invoke() = heroRepository.heroes

}