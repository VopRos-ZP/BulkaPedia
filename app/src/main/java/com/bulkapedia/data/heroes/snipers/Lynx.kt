package com.bulkapedia.data.heroes.snipers

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.GEARS_LIST
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Lynx : Hero() {
    override fun getName(): Int {
        return R.string.lynx
    }

    override fun getBigIcon(): Int {
        return R.drawable.lynx_icon
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
            cell to GEARS_LIST.LYNX_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.cyclops_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.hurricane_icon),
            CounterpickModel(R.drawable.firefly_icon),
            CounterpickModel(R.drawable.dragoon_icon),
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.bertha_icon),
            CounterpickModel(R.drawable.stalker_icon),
            CounterpickModel(R.drawable.doc_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(982, 894, 723,
        700,
            75,
            190,
            114,
            4,
            2.0,
            2.9,
            285,
            330,
            25,
            5,
            20,
            5,
            4,
            1.0,
            1.9,
            52,
            0.8
        )
    }

}