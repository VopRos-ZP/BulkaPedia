package com.bulkapedia.heroes.snipers

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell

class Blot : Hero() {

    override fun getName(): Int {
        return R.string.blot
    }

    override fun getMenuItem(): Int {
        return R.id.blotItem
    }

    override fun getIcon(): Int {
        return R.drawable.blot_menu
    }

    override fun getBigIcon(): Int {
        return R.drawable.blot_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white
        )
    }

    override fun getType(): Int {
        return R.string.snipers
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE
        )
        return cells.mapIndexed { i, cell ->
            cell to GearsList.BLOT_PERSONAL[i]
        }.toMap()
    }

}