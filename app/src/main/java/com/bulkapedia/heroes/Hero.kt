package com.bulkapedia.heroes

import com.bulkapedia.gears.Gear
import com.bulkapedia.sets.GearCell
import java.io.Serializable

abstract class Hero : Serializable {

    abstract fun getName() : Int
    abstract fun getMenuItem() : Int
    abstract fun getIcon() : Int
    abstract fun getBigIcon() : Int
    abstract fun getDifficult() : List<Int>
    abstract fun getType() : Int
    abstract fun getPersonalGears() : Map<GearCell, Gear>

}
