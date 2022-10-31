package com.bulkapedia.heroes.shortguns

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.models.CounterpickModel
import com.bulkapedia.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Arnie : Hero() {

    override fun getName(): Int {
        return R.string.arnie
    }

    override fun getBigIcon(): Int = R.drawable.arnie_icon

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
            cell to GearsList.ARNIE_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.satoshi_icon),
            CounterpickModel(R.drawable.cyclops_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.blot_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            2168, 1445, 264,
            400,
            200,
            178,
            36,
            7,
            3.0,
            1.0,
            135,
            135,
            35,
            10,
            25,
            5,
            4,
            1.0,
            0.5,
            22,
            1.0,
        )
    }

}