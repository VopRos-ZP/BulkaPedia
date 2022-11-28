package com.bulkapedia.data.heroes.shortguns

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearsList
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Sparkle : Hero() {

    override fun getName(): Int {
        return R.string.sparkle
    }

    override fun getBigIcon(): Int = R.drawable.sparkle_icon

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white
        )
    }

    override fun getType(): Int {
        return R.string.shortguns
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE
        )
        return cells.mapIndexed { i, cell ->
            cell to GearsList.SPARKLE_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.cyclops_icon),
            CounterpickModel(R.drawable.hurricane_icon),
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.satoshi_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            2528, 578, 333,
            400,
            400,
            180,
            36,
            7,
            2.5,
            4.0,
            155,
            155,
            10,
            12,
            10,
            2,
            4,
            1.0,
            0.8,
            5,
            1.0
        )
    }

}