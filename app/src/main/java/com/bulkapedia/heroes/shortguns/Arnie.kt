package com.bulkapedia.heroes.shortguns

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell

class Arnie : Hero() {

    override fun getName(): Int {
        return R.string.arnie
    }

    override fun getMenuItem(): Int {
        return R.id.arnieItem
    }

    override fun getIcon(): Int = R.drawable.arnie_menu

    override fun getBigIcon(): Int = R.drawable.arnie_icon

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white
        )
    }

    override fun getType(): Int {
        return R.string.shortguns
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE
        )
        return cells.mapIndexed { i, cell ->
            cell to GearsList.ARNIE_PERSONAL[i]
        }.toMap()
    }


}