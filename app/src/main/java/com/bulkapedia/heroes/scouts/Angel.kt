package com.bulkapedia.heroes.scouts

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.models.CounterpickModel
import com.bulkapedia.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Angel : Hero() {

    override fun getName(): Int {
        return R.string.angel
    }

    override fun getBigIcon(): Int {
        return R.drawable.angel_icon
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
            cell to GearsList.ANGEL_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.smog_icon),
            CounterpickModel(R.drawable.dragoon_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.mirage_icon),
            CounterpickModel(R.drawable.doc_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            520, 1445, 276,
            400,
            300,
            190,
            76,
            4,
            1.5,
            8.0,
            200,
            200,
            30,
            5,
            15,
            8,
            4,
            1.0,
            1.0,
            15,
            0.2
        )
    }

}