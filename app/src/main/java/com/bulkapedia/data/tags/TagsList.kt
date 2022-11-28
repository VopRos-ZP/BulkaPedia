package com.bulkapedia.data.tags

import com.bulkapedia.R
import com.bulkapedia.views.temps.models.TagModel

class TagsList {

    companion object {

        val HEROES_TYPE_TAGS = listOf(
            TagModel(R.color.yellow, R.string.shortguns, false),
            TagModel(R.color.green, R.string.scouts, false),
            TagModel(R.color.error, R.string.snipers, false),
            TagModel(R.color.purple_700, R.string.tanks, false),
            TagModel(R.color.teal_700, R.string.troopers, false),
        )

        val MAPS_MODE_TAGS = listOf(
            TagModel(R.color.yellow, R.string.battle_royale_mode, false),
            TagModel(R.color.green, R.string.kings_of_hill_mode, false),
            TagModel(R.color.error, R.string.sabotage_mode, false),
            TagModel(R.color.purple_700, R.string.squad_mode, false),
        )

    }

}