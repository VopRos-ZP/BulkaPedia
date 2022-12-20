package com.bulkapedia.data.heroes.troopers

import com.bulkapedia.R
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.heroes.Hero
import com.bulkapedia.data.sets.GearCell
import com.bulkapedia.utils.HeroStats
import com.bulkapedia.views.temps.models.CounterpickModel

class Tess : Hero() {
    override fun getName(): Int {
        return R.string.tess
    }

    override fun getBigIcon(): Int {
        return 0
    }

    override fun getDifficult(): List<Int> {
        return listOf(
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow,
            R.drawable.dif_indicator_yellow
        )
    }

    override fun getType(): Int {
        return R.string.troopers
    }

    override fun getPersonalGears(): Map<GearCell, Gear> {
        return emptyMap()
    }

    override fun getCounterpicks(): List<CounterpickModel> {
        return listOf(
            CounterpickModel(R.drawable.arnie_icon),
            CounterpickModel(R.drawable.sparkle_icon),
            CounterpickModel(R.drawable.freddie_icon),
            CounterpickModel(R.drawable.dragoon_icon),
        )
    }

    override fun getStats(): HeroStats {
        return HeroStats(
            911, 1855, 333,
            470,
            400,
            155,
            78,
            5,
            3.0,
            9.0,
            325,
            350,
            50,
            10,
            25,
            40,
            0,
            1.0,
            2.5,
            30,
            0.6
        )
    }

}