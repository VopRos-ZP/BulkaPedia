package com.bulkapedia.data.heroes.shortguns

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearsList
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Hurricane : Hero() {

    override fun getName(): Int {
        return R.string.hurricane
    }

    override fun getBigIcon(): Int {
        return R.drawable.hurricane_icon
    }

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
            cell to GearsList.HURRICANE_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.mirage_icon),
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.smog_icon),
            CounterpickModel(R.drawable.doc_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            1445, 2168, 92,
            400,
            400,
            181,
            36,
            7,
            1.8,
            7.8,
            180,
            180,
            30,
            15,
            25,
            23,
            4,
            1.0,
            0.5,
            10,
            1.0,
        )
    }

}