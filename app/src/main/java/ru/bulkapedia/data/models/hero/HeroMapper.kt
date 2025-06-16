package ru.bulkapedia.data.models.hero

import ru.bulkapedia.domain.model.Hero
import ru.bulkapedia.domain.model.HeroType

fun HeroDto.fromDto(): Hero = Hero(
    id = id,
    imageUrl = imageUrl,
    type = HeroType.valueOf(type)
)

fun Hero.toDto(): HeroDto = HeroDto(
    id = id,
    imageUrl = imageUrl,
    type = type.name
)