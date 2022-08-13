package com.bulkapedia.heroes.scouts

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell

class Freddie : Hero() {

    override fun getName(): Int {
        return R.string.freddie
    }

    override fun getMenuItem(): Int {
        return R.id.freddieItem
    }

    override fun getIcon(): Int {
        return R.drawable.freddie_menu
    }

    override fun getBigIcon(): Int {
        return R.drawable.freddie_icon
    }

    override fun getType(): Int {
        return R.string.scouts
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white,
            R.drawable.dif_indicator_white
        )
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE
        )
        return cells.mapIndexed { i, cell ->
            cell to GearsList.FREDDIE_PERSONAL[i]
        }.toMap()
    }


}