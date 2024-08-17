package ru.bulkapedia.domain.usecase

import ru.bulkapedia.data.room.heroes.FractionDto
import ru.bulkapedia.domain.repository.FractionRepository

class UpsertFractionUseCase(
    private val fractionRepository: FractionRepository
) {

    suspend operator fun invoke(fractions: List<FractionDto>) {
        fractions.forEach { fractionRepository.upsert(it) }
    }

}