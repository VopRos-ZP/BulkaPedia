package com.bulkapedia.heroes.scouts

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell

class Ghost : Hero() {

    override fun getName(): Int {
        return R.string.ghost
    }

    override fun getMenuItem(): Int {
        return R.id.ghostItem
    }

    override fun getIcon(): Int {
        return R.drawable.ghost_menu
    }

    override fun getBigIcon(): Int {
        return R.drawable.ghost_icon
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
            cell to GearsList.GHOST_PERSONAL[i]
        }.toMap()
    }

}