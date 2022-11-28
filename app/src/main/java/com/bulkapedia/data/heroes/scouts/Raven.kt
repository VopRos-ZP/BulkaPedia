package com.bulkapedia.data.heroes.scouts

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearsList
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Raven : Hero() {

    override fun getName(): Int {
        return R.string.raven
    }

    override fun getBigIcon(): Int {
        return R.drawable.raven_icon
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
            cell to GearsList.RAVEN_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.mirage_icon),
            CounterpickModel(R.drawable.dragoon_icon),
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.levi_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            693, 578, 302,
            600,
            300,
            182,
            137,
            4,
            2.0,
            16.0,
            310,
            310,
            20,
            40,
            7,
            15,
            4,
            1.0,
            0.7,
            20,
            0.2,
        )
    }

}