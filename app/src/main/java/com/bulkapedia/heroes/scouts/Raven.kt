package com.bulkapedia.heroes.scouts

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell

class Raven : Hero() {

    override fun getName(): Int {
        return R.string.raven
    }

    override fun getMenuItem(): Int {
        return R.id.ravenItem
    }

    override fun getIcon(): Int {
        return R.drawable.raven_menu
    }

    override fun getBigIcon(): Int {
        return R.drawable.raven_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white,
            R.drawable.dif_indicator_white
        )
    }

    override fun getType(): Int {
        return R.string.scouts
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE
        )
        return cells.mapIndexed { i, cell ->
            cell to GearsList.RAVEN_PERSONAL[i]
        }.toMap()
    }

}