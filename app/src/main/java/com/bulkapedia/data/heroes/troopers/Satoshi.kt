package com.bulkapedia.data.heroes.troopers

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.GEARS_LIST
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Satoshi : Hero() {
    override fun getName(): Int {
        return R.string.satoshi
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
            cell to GEARS_LIST.SATOSHI_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.mirage_icon),
            CounterpickModel(R.drawable.lynx_icon),
            CounterpickModel(R.drawable.smog_icon),
            CounterpickModel(R.drawable.doc_icon),
//            CounterpickModel(R.drawable.tess_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            866, 1985, 280,
            450,
            400,
            152,
            76,
            5,
            1.2,
            9.3,
            265,
            265,
            35,
            30,
            15,
            15,
            4,
            1.0,
            1.0,
            50,
            0.6
        )
    }

}