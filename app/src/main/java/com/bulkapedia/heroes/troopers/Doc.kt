package com.bulkapedia.heroes.troopers

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.sets.GearCell

class Doc : Hero() {
    override fun getName(): Int {
        return R.string.doc
    }

    override fun getMenuItem(): Int {
        return R.id.docItem
    }

    override fun getIcon(): Int {
        return R.drawable.doc_menu
    }

    override fun getBigIcon(): Int {
        return R.drawable.doc_icon
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
            cell to GearsList.DOC_PERSONAL[i]
        }.toMap()
    }

}