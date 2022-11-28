package com.bulkapedia.data.heroes

import com.bulkapedia.data.gears.Gear
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats
import java.io.Serializable

abstract class Hero : Serializable {

    abstract fun getName() : Int
    abstract fun getBigIcon() : Int
    abstract fun getDifficult() : List<Int>
    abstract fun getType() : Int
    abstract fun getPersonalGears() : Map<GearCell, Gear>
    abstract fun getCounterpicks() : List<CounterpickModel>
    abstract fun getStats() : HeroStats

}
