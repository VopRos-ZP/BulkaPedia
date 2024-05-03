package ru.bulkapedia.core

//import ru.bulkapedia.R
//import ru.bulkapedia.data.models.effect.Effect
//import ru.bulkapedia.data.models.hero.Hero
//import ru.bulkapedia.domain.utils.Utils.resourceManager
//
//object PersonalGears {
//
//    fun getPersonalGears(hero: Hero, count: Int) : List<Effect> {
//        return when (hero.id) {
//            "arnie" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.jump_distance_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.team_health_up_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.jump_distance_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.team_health_up_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.fire_rate_effect)),
//                        Effect(-75, false, resourceManager.fromSource(R.string.jump_damage_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "cyclops" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect)),
//                        Effect(100, false, resourceManager.fromSource(R.string.scan_radius_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.team_health_up_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect)),
//                        Effect(100, false, resourceManager.fromSource(R.string.scan_radius_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.team_health_up_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.health_damage_effect)),
//                        Effect(8, true, resourceManager.fromSource(R.string.scan_damage_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "sparkle" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(60, false, resourceManager.fromSource(R.string.max_radius_grenade_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.team_health_up_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(60, false, resourceManager.fromSource(R.string.max_radius_grenade_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.team_health_up_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.add_patrons_effect)),
//                        Effect(12, true, resourceManager.fromSource(R.string.max_damage_grenade_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "hurricane" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.shield_protection_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.team_health_up_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(30, false, resourceManager.fromSource(R.string.health_after_team_health_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.shield_protection_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.team_health_up_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.health_damage_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.shield_duration_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "ghost" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_after_invisible_effect)),
//                        Effect(7, false, resourceManager.fromSource(R.string.stimulator_health_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_after_invisible_effect)),
//                        Effect(7, false, resourceManager.fromSource(R.string.stimulator_health_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.armor_damage_effect)),
//                        Effect(5, true, resourceManager.fromSource(R.string.invisible_damage_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "freddie" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(30, false, resourceManager.fromSource(R.string.max_radius_grenade_effect)),
//                        Effect(7, false, resourceManager.fromSource(R.string.stimulator_health_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(30, false, resourceManager.fromSource(R.string.max_radius_grenade_effect)),
//                        Effect(7, false, resourceManager.fromSource(R.string.stimulator_health_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.fire_rate_effect)),
//                        Effect(20, true, resourceManager.fromSource(R.string.max_damage_grenade_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "angel" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(2, false, resourceManager.fromSource(R.string.shield_protection_effect)),
//                        Effect(7, false, resourceManager.fromSource(R.string.stimulator_health_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(2, false, resourceManager.fromSource(R.string.shield_protection_effect)),
//                        Effect(7, false, resourceManager.fromSource(R.string.stimulator_health_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.add_patrons_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.shield_duration_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "raven" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect)),
//                        Effect(2, false, resourceManager.fromSource(R.string.scan_duration_effect)),
//                        Effect(7, false, resourceManager.fromSource(R.string.stimulator_health_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(5, true, resourceManager.fromSource(R.string.stimulator_speed_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect)),
//                        Effect(2, false, resourceManager.fromSource(R.string.scan_duration_effect)),
//                        Effect(7, false, resourceManager.fromSource(R.string.stimulator_health_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.add_patrons_effect)),
//                        Effect(7, true, resourceManager.fromSource(R.string.scan_damage_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "blot" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.armor_damage_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.armor_damage_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.wall_protection_effect)),
//                        Effect(30, false, resourceManager.fromSource(R.string.band_health_up_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.armor_damage_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.wall_protection_effect)),
//                        Effect(30, false, resourceManager.fromSource(R.string.band_health_up_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.health_damage_effect)),
//                        Effect(15, false, resourceManager.fromSource(R.string.fire_range_while_wall_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "firefly" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.max_radius_grenade_effect)),
//                        Effect(30, false, resourceManager.fromSource(R.string.band_health_up_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.max_radius_grenade_effect)),
//                        Effect(30, false, resourceManager.fromSource(R.string.band_health_up_effect)),
//                        Effect(8, false, resourceManager.fromSource(R.string.piercing_power_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_damage_grenade_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "slayer" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.vision_duration_effect)),
//                        Effect(30, false, resourceManager.fromSource(R.string.band_health_up_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.vision_duration_effect)),
//                        Effect(30, false, resourceManager.fromSource(R.string.band_health_up_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.aiming_speed_effect)),
//                        Effect(2, false, resourceManager.fromSource(R.string.piercing_vision_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "mirage" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.jump_distance_effect)),
//                        Effect(30, false, resourceManager.fromSource(R.string.band_health_up_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.jump_distance_effect)),
//                        Effect(30, false, resourceManager.fromSource(R.string.band_health_up_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_armor_effect)),
//                        Effect(5, true, resourceManager.fromSource(R.string.speed_after_jump_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "lynx" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_armor_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_armor_effect)),
//                        Effect(25, false, ""),
//                        Effect(30, false, resourceManager.fromSource(R.string.band_health_up_effect))
//                    )
//                    6 -> listOf(
//                        Effect(-20, true, resourceManager.fromSource(R.string.band_running_volume_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_armor_effect)),
//                        Effect(25, false, ""),
//                        Effect(30, false, resourceManager.fromSource(R.string.band_health_up_effect)),
//                        Effect(7, false, resourceManager.fromSource(R.string.piercing_power_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.piercing_power_effect))
//                    )
//                    else -> listOf()
//                }
//            }
//            "smog" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_armor_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_armor_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.max_radius_rocket_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.tank_heal_on_activate_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_armor_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.max_radius_rocket_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.tank_heal_on_activate_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.health_damage_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_damage_rocket_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "dragoon" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_effect)),
//                    )
//                    4, 5 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.jump_distance_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.tank_heal_on_activate_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.jump_distance_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.tank_heal_on_activate_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.armor_damage_effect)),
//                        Effect(-75, false, resourceManager.fromSource(R.string.jump_damage_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "bastion" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(-29, true, resourceManager.fromSource(R.string.spread_in_focus_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(-29, true, resourceManager.fromSource(R.string.spread_in_focus_effect)),
//                        Effect(3, false, resourceManager.fromSource(R.string.shield_protection_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.tank_heal_on_activate_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(-29, true, resourceManager.fromSource(R.string.spread_in_focus_effect)),
//                        Effect(3, false, resourceManager.fromSource(R.string.shield_protection_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.tank_heal_on_activate_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.shield_duration_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "bertha" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_armor_effect)),
//                    )
//                    4, 5 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_armor_effect)),
//                        Effect(-10, true, resourceManager.fromSource(R.string.reloading_after_suppression_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.tank_heal_on_activate_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_armor_effect)),
//                        Effect(-10, true, resourceManager.fromSource(R.string.reloading_after_suppression_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.tank_heal_on_activate_effect)),
//                        Effect(7, false, resourceManager.fromSource(R.string.piercing_power_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.piercing_on_suppression_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "leviathan" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect)),
//                    )
//                    4, 5 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.turret_damage_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.tank_heal_on_activate_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(10, true, resourceManager.fromSource(R.string.max_health_after_tank_heal_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_not_focus_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.turret_damage_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.tank_heal_on_activate_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.turret_health_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "stalker" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_effect)),
//                    )
//                    4, 5 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.invisible_duration_effect)),
//                        Effect(-20, true, resourceManager.fromSource(R.string.buff_damage_trooper_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.speed_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.invisible_duration_effect)),
//                        Effect(-20, true, resourceManager.fromSource(R.string.buff_damage_trooper_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.armor_damage_effect)),
//                        Effect(5, true, resourceManager.fromSource(R.string.invisible_damage_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "doc" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.fire_rate_effect)),
//                    )
//                    4, 5 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.fire_rate_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.max_radius_rocket_effect)),
//                        Effect(-20, true, resourceManager.fromSource(R.string.buff_damage_trooper_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.fire_rate_effect)),
//                        Effect(50, false, resourceManager.fromSource(R.string.max_radius_rocket_effect)),
//                        Effect(-20, true, resourceManager.fromSource(R.string.buff_damage_trooper_effect)),
//                        Effect(9, true, resourceManager.fromSource(R.string.health_damage_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.max_damage_rocket_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "levi" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                    )
//                    4, 5 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.vision_duration_effect)),
//                        Effect(-20, true, resourceManager.fromSource(R.string.buff_damage_trooper_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.vision_duration_effect)),
//                        Effect(-20, true, resourceManager.fromSource(R.string.buff_damage_trooper_effect)),
//                        Effect(-29, true, resourceManager.fromSource(R.string.spread_in_focus_effect)),
//                        Effect(2, false, resourceManager.fromSource(R.string.piercing_vision_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "satoshi" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_focus_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_focus_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.wall_duration_effect)),
//                        Effect(-20, true, resourceManager.fromSource(R.string.buff_damage_trooper_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(-18, true, resourceManager.fromSource(R.string.spread_in_focus_effect)),
//                        Effect(1, false, resourceManager.fromSource(R.string.wall_duration_effect)),
//                        Effect(-20, true, resourceManager.fromSource(R.string.buff_damage_trooper_effect)),
//                        Effect(21, false, resourceManager.fromSource(R.string.fire_range_focus_effect)),
//                        Effect(5, false, resourceManager.fromSource(R.string.wall_protection_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            "tess" -> {
//                when (count) {
//                    2, 3 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect))
//                    )
//                    4, 5 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(25, false, resourceManager.fromSource(R.string.ball_radius_effect)),
//                        Effect(-20, true, resourceManager.fromSource(R.string.buff_damage_trooper_effect)),
//                    )
//                    6 -> listOf(
//                        Effect(10, false, resourceManager.fromSource(R.string.buff_health_trooper_effect)),
//                        Effect(-21, true, resourceManager.fromSource(R.string.reloading_time_effect)),
//                        Effect(25, false, resourceManager.fromSource(R.string.ball_radius_effect)),
//                        Effect(-20, true, resourceManager.fromSource(R.string.buff_damage_trooper_effect)),
//                        Effect(10, true, resourceManager.fromSource(R.string.armor_damage_effect)),
//                        Effect(-10, true, resourceManager.fromSource(R.string.ball_incoming_damage_effect)),
//                    )
//                    else -> listOf()
//                }
//            }
//            else -> listOf()
//        }
//    }
//}