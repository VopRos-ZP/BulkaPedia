package com.bulkapedia.views.temps.models

import com.bulkapedia.data.heroes.Hero
import java.io.Serializable

data class HeroModel (
    val hero: Hero,
    val heroIcon: Int,
    val heroName: Int,
    val heroCounterpick: List<CounterpickModel>
) : Serializable