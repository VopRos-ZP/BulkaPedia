package com.bulkapedia.data.heroes.tanks

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearsList
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Smog : Hero() {
    override fun getName(): Int {
        return R.string.smog
    }

    override fun getBigIcon(): Int {
        return R.drawable.smog_icon
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_white
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
            cell to GearsList.SMOG_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.freddie_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.cyclops_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.mirage_icon),
            CounterpickModel(R.drawable.firefly_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            3431, 1805, 80,
            400,
            400,
            125,
            25,
            11,
            5.0,
            18.0,
            245,
            245,
            30,
            20,
            30,
            100,
            4,
            1.0,
            2.6,
            50,
            1.0
        )
    }

}