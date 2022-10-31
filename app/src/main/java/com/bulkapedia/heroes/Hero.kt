package com.bulkapedia.heroes

import com.bulkapedia.gears.Gear
import com.bulkapedia.models.CounterpickModel
import com.bulkapedia.sets.GearCell
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
