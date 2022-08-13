package com.bulkapedia.heroes.troopers

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell

class Satoshi : Hero() {
    override fun getName(): Int {
        return R.string.satoshi
    }

    override fun getMenuItem(): Int {
        return R.id.satoshiItem
    }

    override fun getIcon(): Int {
        return R.drawable.satoshi_menu
    }

    override fun getBigIcon(): Int {
        return R.drawable.satoshi_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white
        )
    }

    override fun getType(): Int {
        return R.string.troopers
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE
        )
        return cells.mapIndexed { i, cell ->
            cell to GearsList.SATOSHI_PERSONAL[i]
        }.toMap()
    }


}