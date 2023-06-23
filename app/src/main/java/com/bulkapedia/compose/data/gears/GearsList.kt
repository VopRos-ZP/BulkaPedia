package com.bulkapedia.compose.data.gears

import com.bulkapedia.R
import com.bulkapedia.compose.data.repos.gears.Effect
import com.bulkapedia.compose.data.repos.gears.Gear
import com.bulkapedia.compose.data.repos.gears.GearSet
import com.bulkapedia.compose.data.repos.heroes.Hero
import com.bulkapedia.compose.data.repos.sets.GearCell
import com.bulkapedia.compose.util.resourceToString
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class GearsList {

    fun getGears(func: (List<Gear>) -> Unit) {
        getAllGears { all, _ -> func.invoke(all) }
    }

    fun getDefaultGears(func: (List<Gear>) -> Unit) {
        getAllGears { _, def -> func.invoke(def) }
    }

    private fun getAllGears(function: (List<Gear>, List<Gear>) -> Unit) {
        Firebase.firestore.collection("gears").addSnapshotListener { value, _ ->
            if (value != null) {
                val allGears = value.documents.map { doc -> doc.toObject<Gear>()!! }
                val allDefaultGears = allGears.filter { it.gearSet == GearSet.NONE.name }
                function.invoke(allGears, allDefaultGears)
            }
        }
    }

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

    fun getSetsGears(hero: Hero) : Map<GearCell, Gear> {
        val cells = listOf(
            GearCell.HEAD, GearCell.BODY,
            GearCell.ARM, GearCell.LEG,
            GearCell.DECOR, GearCell.DEVICE,
        )
        return when (hero.type) {
            "tanks" -> emptyMap()
            "scouts" -> emptyMap()
            "snipers" -> emptyMap()
            "troopers" -> emptyMap()
            "shortguns" -> emptyMap()
            else -> emptyMap()
        }
    }

}