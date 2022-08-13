package com.bulkapedia.gears

import java.io.Serializable

data class Gear(
    val gearSet : GearSet,
    val icon : Int,
    val effects : List<Effect>
) : Serializable