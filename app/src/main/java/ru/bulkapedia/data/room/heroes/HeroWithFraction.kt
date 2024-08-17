package ru.bulkapedia.data.room.heroes

import androidx.room.Embedded
import androidx.room.Relation


data class HeroWithFraction(
    @Embedded val heroDto: HeroDto,
    @Relation(
        parentColumn = "fraction",
        entityColumn = "id"
    )
    val fractionDto: FractionDto
)
