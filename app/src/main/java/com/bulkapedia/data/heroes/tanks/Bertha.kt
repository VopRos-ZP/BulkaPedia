package com.bulkapedia.data.heroes.tanks

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearsList
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Bertha : Hero() {
    override fun getName(): Int {
        return R.string.bertha
    }

    override fun getBigIcon(): Int {
        return R.drawable.bertha_icon
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
            cell to GearsList.BERTHA_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.satoshi_icon),
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.hurricane_icon),
            CounterpickModel(R.drawable.freddie_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.blot_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            2600, 2643, 227,
            400,
            400,
            132,
            13,
            11,
            4.0,
            15.0,
            160,
            430,
            60,
            50,
            14,
            60,
            4,
            1.0,
            1.7,
            20,
            0.1
        )
    }

}