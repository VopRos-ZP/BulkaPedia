package com.bulkapedia.heroes.tanks

import com.bulkapedia.R
import com.bulkapedia.gears.Gear
import com.bulkapedia.gears.GearsList
import com.bulkapedia.heroes.Hero
import com.bulkapedia.models.CounterpickModel
import com.bulkapedia.sets.GearCell
import com.bulkapedia.utils.HeroStats

class Dragoon : Hero() {
    override fun getName(): Int {
        return R.string.dragoon
    }

    override fun getBigIcon(): Int {
        return R.drawable.dragoon_icon
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
            cell to GearsList.DRAGOON_PERSONAL[i]
        }.toMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.satoshi_icon),
            CounterpickModel(R.drawable.freddie_icon),
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.bastion_icon),
            CounterpickModel(R.drawable.doc_icon),
            CounterpickModel(R.drawable.firefly_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            2168, 2528, 137,
            400,
            200,
            180,
            18,
            11,
            1.5,
            11.0,
            255,
            255,
            20,
            2,
            15,
            26,
            4,
            1.0,
            3.2,
            19,
            1.0
        )
    }

}