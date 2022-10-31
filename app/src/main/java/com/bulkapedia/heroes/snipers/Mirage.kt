package com.bulkapedia.heroes.snipers

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.models.CounterpickModel
import com.bulkapedia.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Mirage : Hero() {

    override fun getName(): Int {
        return R.string.mirage
    }

    override fun getBigIcon(): Int {
        return R.drawable.mirage_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white
        )
    }

    override fun getType(): Int {
        return R.string.snipers
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE
        )
        return cells.mapIndexed { i, cell ->
            cell to GearsList.MIRAGE_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.firefly_icon),
            CounterpickModel(R.drawable.freddie_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.bertha_icon),
            CounterpickModel(R.drawable.dragoon_icon),
            CounterpickModel(R.drawable.levi_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            1300, 865, 578,
            600,
            100,
            170,
            170,
            4,
            5.0,
            1.1,
            370,
            380,
            3,
            0,
            2,
            10,
            8,
            1.0,
            1.9,
            80,
            0.0
        )
    }

}