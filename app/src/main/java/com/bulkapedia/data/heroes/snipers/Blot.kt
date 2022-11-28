package com.bulkapedia.data.heroes.snipers

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearsList
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.views.temps.models.CounterpickModel
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Blot : Hero() {

    override fun getName(): Int {
        return R.string.blot
    }

    override fun getBigIcon(): Int {
        return R.drawable.blot_icon
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
            cell to GearsList.BLOT_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.mirage_icon),
            CounterpickModel(R.drawable.firefly_icon),
            CounterpickModel(R.drawable.lynx_icon),
            CounterpickModel(R.drawable.smog_icon),
            CounterpickModel(R.drawable.doc_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            578, 1560, 2141,
            600,
            300,
            172,
            69,
            4,
            4.0,
            0.4,
            400,
            400,
            45,
            9,
            7,
            11,
            10,
            1.0,
            2.0,
            60,
            0.4
        )
    }

}