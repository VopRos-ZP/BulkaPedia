package com.bulkapedia.data.heroes.snipers

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.GEARS_LIST
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Slayer : Hero() {

    override fun getName(): Int {
        return R.string.slayer
    }

    override fun getBigIcon(): Int {
        return R.drawable.slayer_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow
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
            cell to GEARS_LIST.SLAYER_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.freddie_icon),
            CounterpickModel(R.drawable.ghost_icon),
            CounterpickModel(R.drawable.raven_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.hurricane_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.blot_icon),
            CounterpickModel(R.drawable.mirage_icon),
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.bertha_icon),
            CounterpickModel(R.drawable.dragoon_icon),
            CounterpickModel(R.drawable.levi_icon),
            CounterpickModel(R.drawable.satoshi_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            792, 792, 2497,
            600,
            300,
            171,
            51,
            4,
            5.0,
            0.3,
            430,
            430,
            50,
            5,
            5,
            5,
            22,
            1.0,
            2.0,
            85,
            1.7
        )
    }

}