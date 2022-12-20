package com.bulkapedia.data.heroes.tanks

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.GEARS_LIST
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Bastion : Hero() {
    override fun getName(): Int {
        return R.string.bastion
    }

    override fun getBigIcon(): Int {
        return R.drawable.bastion_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white,
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
            cell to GEARS_LIST.BASTION_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.doc_icon),
            CounterpickModel(R.drawable.smog_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.mirage_icon),
//            CounterpickModel(R.drawable.tess_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            1905, 3613, 152,
            400,
            400,
            131,
            13,
            11,
            5.0,
            21.0,
            210,
            210,
            20,
            45,
            20,
            100,
            5,
            1.0,
            1.2,
            60,
            1.5
        )
    }

}