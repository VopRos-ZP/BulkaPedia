package ru.bulkapedia.core

//import ru.bulkapedia.R
//import ru.bulkapedia.domain.utils.Utils.resourceManager
//import ru.bulkapedia.data.models.effect.Effect
//import ru.bulkapedia.data.models.gear.Gear
//import ru.bulkapedia.data.models.gear.GearSet
//
//object GearsList {
//
//    fun getEffectsFromSets(gears: List<Gear>): List<Effect> {
//        val effects = mutableListOf<Effect>()
//        when (gears.filter { it.gearSet == GearSet.BIO_NODE }.size) {
//            2, 3 -> effects.add(Effect(number = 2, percent = false, description = resourceManager.fromSource(R.string.piercing_effect)))
//            4, 5 -> {
//                effects.add(Effect(number = 2, percent = false, description = resourceManager.fromSource(R.string.piercing_effect)))
//                effects.add(Effect(number = 3, false, resourceManager.fromSource(R.string.add_patrons_effect)))
//            }
//            6 -> {
//                effects.add(Effect(number = 2, false, resourceManager.fromSource(R.string.piercing_effect)))
//                effects.add(Effect(3, false, resourceManager.fromSource(R.string.add_patrons_effect)))
//                effects.add(Effect(-11, true, resourceManager.fromSource(R.string.running_volume_effect)))
//            }
//        }
//        when (gears.filter { it.gearSet == GearSet.DARK_IMPLANT }.size) {
//            2, 3 -> effects.add(Effect(5, true, resourceManager.fromSource(R.string.health_damage_effect)))
//            4, 5 -> {
//                effects.add(Effect(5, true, resourceManager.fromSource(R.string.health_damage_effect)))
//                effects.add(Effect(10, false, resourceManager.fromSource(R.string.fire_range_focus_effect)))
//            }
//            6 -> {
//                effects.add(Effect(5, true, resourceManager.fromSource(R.string.health_damage_effect)))
//                effects.add(Effect(10, false, resourceManager.fromSource(R.string.fire_range_focus_effect)))
//                effects.add(Effect(-10, true, resourceManager.fromSource(R.string.reloading_time_effect)))
//            }
//        }
//        when (gears.filter { it.gearSet == GearSet.WHITE_INDEX }.size) {
//            2, 3 -> effects.add(Effect(5, true, resourceManager.fromSource(R.string.armor_damage_effect)))
//            4, 5 -> {
//                effects.add(Effect(5, true, resourceManager.fromSource(R.string.armor_damage_effect)))
//                effects.add(Effect(-10, true, resourceManager.fromSource(R.string.reloading_time_effect)))
//            }
//            6 -> {
//                effects.add(Effect(5, true, resourceManager.fromSource(R.string.armor_damage_effect)))
//                effects.add(Effect(-10, true, resourceManager.fromSource(R.string.reloading_time_effect)))
//                effects.add(Effect(10, false, resourceManager.fromSource(R.string.add_health_effect)))
//            }
//        }
//        when (gears.filter { it.gearSet == GearSet.HEAVY_PORT }.size) {
//            2, 3 -> effects.add(Effect(2, false, resourceManager.fromSource(R.string.piercing_effect)))
//            4, 5 -> {
//                effects.add(Effect(2, false, resourceManager.fromSource(R.string.piercing_effect)))
//                effects.add(Effect(3, true, resourceManager.fromSource(R.string.max_armor_effect)))
//            }
//            6 -> {
//                effects.add(Effect(2, false, resourceManager.fromSource(R.string.piercing_effect)))
//                effects.add(Effect(3, true, resourceManager.fromSource(R.string.max_armor_effect)))
//                effects.add(Effect(5, true, resourceManager.fromSource(R.string.armor_damage_effect)))
//            }
//        }
//        when (gears.filter { it.gearSet == GearSet.PART }.size) {
//            2, 3 -> effects.add(Effect(5, true, resourceManager.fromSource(R.string.fire_rate_effect)))
//            4, 5 -> {
//                effects.add(Effect(5, true, resourceManager.fromSource(R.string.fire_rate_effect)))
//                effects.add(Effect(10, false, resourceManager.fromSource(R.string.fire_range_focus_effect)))
//            }
//            6 -> {
//                effects.add(Effect(5, true, resourceManager.fromSource(R.string.fire_rate_effect)))
//                effects.add(Effect(10, false, resourceManager.fromSource(R.string.fire_range_focus_effect)))
//                effects.add(Effect(-11, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect)))
//            }
//        }
//        return effects
//    }
//
//}