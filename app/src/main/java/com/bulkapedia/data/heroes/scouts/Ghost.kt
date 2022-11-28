package com.bulkapedia.data.heroes.scouts

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearsList
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Ghost : Hero() {

    override fun getName(): Int {
        return R.string.ghost
    }

    override fun getBigIcon(): Int {
        return R.drawable.ghost_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white,
            R.drawable.dif_indicator_white
        )
    }

    override fun getType(): Int {
        return R.string.scouts
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE
        )
        return cells.mapIndexed { i, cell ->
            cell to GearsList.GHOST_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.dragoon_icon),
            CounterpickModel(R.drawable.cyclops_icon),
            CounterpickModel(R.drawable.raven_icon),
            CounterpickModel(R.drawable.smog_icon),
            CounterpickModel(R.drawable.bertha_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            902, 578, 248,
            400,
            100,
            189,
            76,
            4,
            1.8,
            14.4,
            235,
            235,
            35,
            10,
            20,
            13,
            4,
            1.0,
            0.7,
            33,
            0.2
        )
    }

}