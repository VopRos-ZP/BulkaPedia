package com.bulkapedia.data.gears

import com.bulkapedia.data.labels.Ranks
import java.io.Serializable

data class Gear(
    val gearSet : GearSet,
    val icon : Int,
    val effects : List<Effect>,
    val rankEffect : Map<Ranks, List<Effect>>
) : Serializable