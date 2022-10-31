package com.bulkapedia.heroes.scouts

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.models.CounterpickModel
import com.bulkapedia.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Freddie : Hero() {

    override fun getName(): Int {
        return R.string.freddie
    }

    override fun getBigIcon(): Int {
        return R.drawable.freddie_icon
    }

    override fun getType(): Int {
        return R.string.scouts
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white,
            R.drawable.dif_indicator_white
        )
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE
        )
        return cells.mapIndexed { i, cell ->
            cell to GearsList.FREDDIE_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.satoshi_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            1262, 288, 227,
            400,
            300,
            188,
            75,
            4,
            3.0,
            15.0,
            205,
            205,
            25,
            15,
            20,
            28,
            4,
            1.0,
            0.7,
            20,
            2.0
        )
    }

}