package com.bulkapedia.heroes.shortguns

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell

class Cyclops : Hero() {

    override fun getName(): Int {
        return R.string.cyclops
    }

    override fun getMenuItem(): Int {
        return R.id.cyclopsItem
    }

    override fun getIcon(): Int = R.drawable.cyclops_menu

    override fun getBigIcon(): Int = R.drawable.cyclops_icon

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow
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
            cell to GearsList.CYCLOPS_PERSONAL[i]
        }.toMap()
    }


}