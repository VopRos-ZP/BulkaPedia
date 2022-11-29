package com.bulkapedia.data.heroes.troopers

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.GEARS_LIST
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Doc : Hero() {
    override fun getName(): Int {
        return R.string.doc
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
            cell to GEARS_LIST.DOC_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.freddie_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.mirage_icon),
            CounterpickModel(R.drawable.bertha_icon),
            CounterpickModel(R.drawable.levi_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            1805, 1445, 214,
            400,
            400,
            150,
            75,
            5,
            5.0,
            12.0,
            275,
            275,
            30,
            40,
            15,
            70,
            4,
            1.0,
            1.0,
            40,
            0.6
        )
    }

}