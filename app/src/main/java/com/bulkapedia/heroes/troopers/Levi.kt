package com.bulkapedia.heroes.troopers

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.models.CounterpickModel
import com.bulkapedia.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Levi : Hero() {
    override fun getName(): Int {
        return R.string.levi
    }

    override fun getBigIcon(): Int {
        return R.drawable.levi_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white,
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
            cell to GearsList.LEVI_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.freddie_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.hurricane_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.blot_icon),
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.bertha_icon),
            CounterpickModel(R.drawable.satoshi_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            1262, 1010, 723,
            600,
            400,
            151,
            76,
            5,
            4.0,
            3.8,
            360,
            360,
            35,
            30,
            10,
            15,
            8,
            1.0,
            1.0,
            45,
            0.2
        )
    }

}