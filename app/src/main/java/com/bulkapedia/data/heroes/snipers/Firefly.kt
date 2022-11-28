package com.bulkapedia.data.heroes.snipers

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearsList
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Firefly : Hero() {

    override fun getName(): Int {
        return R.string.firefly
    }

    override fun getBigIcon(): Int {
        return R.drawable.firefly_icon
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
            cell to GearsList.FIREFLY_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.freddie_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.hurricane_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.bertha_icon),
            CounterpickModel(R.drawable.levi_icon),
            CounterpickModel(R.drawable.stalker_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            1985, 288, 728,
            600,
            300,
            169,
            85,
            4,
            4.0,
            1.2,
            220,
            435,
            14,
            10,
            8,
            20,
            8,
            1.2,
            2.0,
            55,
            0.4
        )
    }

}