package ru.bulkapedia.domain.usecase

import ru.bulkapedia.domain.repository.FractionRepository

class GetFractionsUseCase(
    private val fractionRepository: FractionRepository
) {

    operator fun invoke() = fractionRepository.fractions

}