package com.bulkapedia.heroes.troopers

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.models.CounterpickModel
import com.bulkapedia.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Stalker : Hero() {
    override fun getName(): Int {
        return R.string.stalker
    }

    override fun getBigIcon(): Int {
        return R.drawable.stalker_icon
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
            cell to GearsList.STALKER_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.freddie_icon),
            CounterpickModel(R.drawable.raven_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.cyclops_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.bertha_icon),
            CounterpickModel(R.drawable.smog_icon),
            CounterpickModel(R.drawable.doc_icon),
            CounterpickModel(R.drawable.levi_icon),
            CounterpickModel(R.drawable.satoshi_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            1262, 1156, 347,
            400,
            160,
            184,
            92,
            5,
            3.0,
            8.0,
            285,
            285,
            22,
            0,
            12,
            30,
            4,
            1.0,
            1.0,
            35,
            0.6
        )
    }

}