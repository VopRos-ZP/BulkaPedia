package ru.bulkapedia.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.bulkapedia.domain.model.Fraction

interface FractionRepository {
    val fractions: Flow<List<Fraction>>
}