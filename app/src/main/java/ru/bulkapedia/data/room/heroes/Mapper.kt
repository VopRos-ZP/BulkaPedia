package ru.bulkapedia.data.room.heroes

import ru.bulkapedia.domain.model.hero.Hero
import ru.bulkapedia.domain.model.hero.HeroType

fun HeroWithFraction.fromDto() = Hero(
    id = heroDto.id,
    isActive = heroDto.isActive,
    fraction = fractionDto.id,
    fractionImageUrl = fractionDto.imageUrl,
    type = HeroType.valueOf(heroDto.type),
    imageUrl = heroDto.imageUrl
)

fun Hero.toDto() = HeroWithFraction(
    heroDto = HeroDto(
        id = id,
        isActive = isActive,
        fraction = fraction,
        type = type.name,
        imageUrl = imageUrl
    ),
    fractionDto = FractionDto(
        id = fraction,
        imageUrl = fractionImageUrl
    )
)
