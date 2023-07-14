package com.bulkapedia.compose.data.gears

import com.bulkapedia.R
import com.bulkapedia.compose.util.resourceToString
import bulkapedia.effects.Effect
import bulkapedia.gears.Gear
import bulkapedia.gears.GearSet

class GearsList {

    fun getEffectsFromSets(gears: List<Gear>): List<Effect> {
        val effects = mutableListOf<Effect>()
        when (gears.filter { it.set == GearSet.BIO_NODE }.size) {
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
        when (gears.filter { it.set == GearSet.DARK_IMPLANT }.size) {
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
        when (gears.filter { it.set == GearSet.WHITE_INDEX }.size) {
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
        when (gears.filter { it.set == GearSet.HEAVY_PORT }.size) {
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
        when (gears.filter { it.set == GearSet.PARTS }.size) {
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