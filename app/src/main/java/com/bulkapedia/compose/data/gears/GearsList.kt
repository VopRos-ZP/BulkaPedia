package com.bulkapedia.compose.data.gears

import com.bulkapedia.R
import com.bulkapedia.compose.util.resourceToString
import com.bulkapedia.data.gears.Effect
import com.bulkapedia.data.gears.Gear
import com.bulkapedia.data.gears.GearSet

class GearsList {

    fun getEffectsFromSets(gears: List<Gear>): List<Effect> {
        val effects = mutableListOf<Effect>()
        when (gears.filter { it.gearSet == GearSet.BIO_NODE.name }.size) {
            2, 3 -> effects.add(Effect(2, false, resourceToString(R.string.piercing_effect)))
            4, 5 -> {
                effects.add(Effect(2, false, resourceToString(R.string.piercing_effect)))
                effects.add(Effect(3, false, resourceToString(R.string.add_patrons_effect)))
            }
            6 -> {
                effects.add(Effect(2, false, resourceToString(R.string.piercing_effect)))
                effects.add(Effect(3, false, resourceToString(R.string.add_patrons_effect)))
                effects.add(Effect(-11, true, resourceToString(R.string.running_volume_effect)))
            }
        }
        when (gears.filter { it.gearSet == GearSet.DARK_IMPLANT.name }.size) {
            2, 3 -> effects.add(Effect(5, true, resourceToString(R.string.health_damage_effect)))
            4, 5 -> {
                effects.add(Effect(5, true, resourceToString(R.string.health_damage_effect)))
                effects.add(Effect(10, false, resourceToString(R.string.fire_range_focus_effect)))
            }
            6 -> {
                effects.add(Effect(5, true, resourceToString(R.string.health_damage_effect)))
                effects.add(Effect(10, false, resourceToString(R.string.fire_range_focus_effect)))
                effects.add(Effect(-10, true, resourceToString(R.string.reloading_time_effect)))
            }
        }
        when (gears.filter { it.gearSet == GearSet.WHITE_INDEX.name }.size) {
            2, 3 -> effects.add(Effect(5, true, resourceToString(R.string.armor_damage_effect)))
            4, 5 -> {
                effects.add(Effect(5, true, resourceToString(R.string.armor_damage_effect)))
                effects.add(Effect(-10, true, resourceToString(R.string.reloading_time_effect)))
            }
            6 -> {
                effects.add(Effect(5, true, resourceToString(R.string.armor_damage_effect)))
                effects.add(Effect(-10, true, resourceToString(R.string.reloading_time_effect)))
                effects.add(Effect(10, false, resourceToString(R.string.add_health_effect)))
            }
        }
        when (gears.filter { it.gearSet == GearSet.HEAVY_PORT.name }.size) {
            2, 3 -> effects.add(Effect(2, false, resourceToString(R.string.piercing_effect)))
            4, 5 -> {
                effects.add(Effect(2, false, resourceToString(R.string.piercing_effect)))
                effects.add(Effect(3, true, resourceToString(R.string.max_armor_effect)))
            }
            6 -> {
                effects.add(Effect(2, false, resourceToString(R.string.piercing_effect)))
                effects.add(Effect(3, true, resourceToString(R.string.max_armor_effect)))
                effects.add(Effect(5, true, resourceToString(R.string.armor_damage_effect)))
            }
        }
        when (gears.filter { it.gearSet == GearSet.PARTS.name }.size) {
            2, 3 -> effects.add(Effect(5, true, resourceToString(R.string.fire_rate_effect)))
            4, 5 -> {
                effects.add(Effect(5, true, resourceToString(R.string.fire_rate_effect)))
                effects.add(Effect(10, false, resourceToString(R.string.fire_range_focus_effect)))
            }
            6 -> {
                effects.add(Effect(5, true, resourceToString(R.string.fire_rate_effect)))
                effects.add(Effect(10, false, resourceToString(R.string.fire_range_focus_effect)))
                effects.add(Effect(-11, true, resourceToString(R.string.spread_in_not_focus_effect)))
            }
        }
        return effects
    }

}