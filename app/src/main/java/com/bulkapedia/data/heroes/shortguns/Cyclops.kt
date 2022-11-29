package com.bulkapedia.data.heroes.shortguns

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.GEARS_LIST
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Cyclops : Hero() {

    override fun getName(): Int {
        return R.string.cyclops
    }

    override fun getBigIcon(): Int = R.drawable.cyclops_icon

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow
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
            cell to GEARS_LIST.CYCLOPS_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.angel_icon),
            CounterpickModel(R.drawable.hurricane_icon),
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.dragoon_icon),
            CounterpickModel(R.drawable.bertha_icon),
            CounterpickModel(R.drawable.levi_icon),
            CounterpickModel(R.drawable.mirage_icon),
            CounterpickModel(R.drawable.firefly_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            1444, 1805, 185,
            450,
            400,
            179,
            139,
            7,
            3.0,
            3.1,
            200,
            275,
            25,
            30,
            10,
            12,
            4,
            1.0,
            0.5,
            15,
            0.7
        )
    }

}