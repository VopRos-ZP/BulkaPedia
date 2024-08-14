package ru.bulkapedia.data.repository.fraction

import ru.bulkapedia.data.repository.hero.Fraction

fun Fraction.toPojo() = ru.bulkapedia.domain.model.Fraction(
    id = id,
    imageUrl = imageUrl
)