package ru.bulkapedia.data.models.hero

import ru.bulkapedia.domain.model.Hero
import ru.bulkapedia.domain.model.HeroType

fun HeroDto.toHero() = Hero(
    id = id,
    type = HeroType.valueOf(type),
    active = active,
    image = image
)

fun Hero.toDto() = HeroDto(
    id = id,
    type = type.name,
    active = active,
    image = image
)