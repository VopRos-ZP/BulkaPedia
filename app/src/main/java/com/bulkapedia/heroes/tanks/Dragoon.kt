package com.bulkapedia.heroes.tanks

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell

class Dragoon : Hero() {
    override fun getName(): Int {
        return R.string.dragoon
    }

    override fun getMenuItem(): Int {
        return R.id.dragoonItem
    }

    override fun getIcon(): Int {
        return R.drawable.dragoon_menu
    }

    override fun getBigIcon(): Int {
        return R.drawable.dragoon_icon
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
            cell to GearsList.DRAGOON_PERSONAL[i]
        }.toMap()
    }


}