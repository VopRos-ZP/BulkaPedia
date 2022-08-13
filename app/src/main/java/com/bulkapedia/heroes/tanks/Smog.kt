package com.bulkapedia.heroes.tanks

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell

class Smog : Hero() {
    override fun getName(): Int {
        return R.string.smog
    }

    override fun getMenuItem(): Int {
        return R.id.smogItem
    }

    override fun getIcon(): Int {
        return R.drawable.smog_menu
    }

    override fun getBigIcon(): Int {
        return R.drawable.smog_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white
        )
    }

    override fun getType(): Int {
        return R.string.tanks
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE
        )
        return cells.mapIndexed { i, cell ->
            cell to GearsList.SMOG_PERSONAL[i]
        }.toMap()
    }

}