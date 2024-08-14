package ru.bulkapedia.data.repository.hero

fun Hero.toPojo() = ru.bulkapedia.domain.model.hero.Hero(
    id = id,
    isActive = isActive,
    fraction = fraction,
    difficult = difficult,
    type  = type,
    imageUrl = imageUrl
)