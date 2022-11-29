package com.bulkapedia.data.heroes.tanks

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.GEARS_LIST
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Leviathan : Hero() {
    override fun getName(): Int {
        return R.string.leviathan
    }

    override fun getBigIcon(): Int {
        return R.drawable.leviathan_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow
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
            cell to GEARS_LIST.LEVIATHAN_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.satoshi_icon),
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.hurricane_icon),
            CounterpickModel(R.drawable.blot_icon),
            CounterpickModel(R.drawable.firefly_icon),
            CounterpickModel(R.drawable.bertha_icon),
            CounterpickModel(R.drawable.doc_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            1879, 2292, 103,
            500,
            400,
            133,
            13,
            11,
            3.5,
            25.0,
            348,
            348,
            55,
            5,
            35,
            200,
            11,
            1.0,
            1.7,
            80,
            1.5
        )
    }

}