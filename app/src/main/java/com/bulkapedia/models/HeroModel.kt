package com.bulkapedia.models

import com.bulkapedia.heroes.Hero
import java.io.Serializable

data class HeroModel (
    val hero: Hero,
    val heroIcon: Int,
    val heroName: Int
) : Serializable