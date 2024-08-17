package ru.bulkapedia.domain.usecase

import ru.bulkapedia.domain.repository.HeroRepository

class GetHeroesUseCase(
    private val heroRepository: HeroRepository,
) : BaseUseCase() {

    operator fun invoke() =
        heroRepository.listenAll().stateIn()

}