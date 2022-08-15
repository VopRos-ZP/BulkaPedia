package com.bulkapedia.gears

import com.bulkapedia.R
import com.bulkapedia.heroes.*

class PersonalGears {

    companion object {
        fun getPersonalGears(hero: Hero, count: Int) : List<Effect> {
            return when (hero) {
                HeroList.ARNIE -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(10, true, R.string.speed_effect)
                        )
                        4, 5 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(10, true, R.string.speed_effect),
                            Effect(50, false, R.string.jump_distance_effect),
                            Effect(5, false, R.string.team_health_up_effect),
                        )
                        6 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(10, true, R.string.speed_effect),
                            Effect(50, false, R.string.jump_distance_effect),
                            Effect(5, false, R.string.team_health_up_effect),
                            Effect(9, true, R.string.fire_rate_effect),
                            Effect(-75, false, R.string.jump_damage_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.CYCLOPS -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect)
                        )
                        4, 5 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect),
                            Effect(100, false, R.string.scan_radius_effect),
                            Effect(5, false, R.string.team_health_up_effect),
                        )
                        6 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect),
                            Effect(100, false, R.string.scan_radius_effect),
                            Effect(5, false, R.string.team_health_up_effect),
                            Effect(9, true, R.string.health_damage_effect),
                            Effect(8, true, R.string.scan_damage_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.SPARKLE -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(-21, true, R.string.reloading_time_effect)
                        )
                        4, 5 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(60, false, R.string.max_radius_grenade_effect),
                            Effect(5, false, R.string.team_health_up_effect),
                        )
                        6 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(60, false, R.string.max_radius_grenade_effect),
                            Effect(5, false, R.string.team_health_up_effect),
                            Effect(1, false, R.string.add_patrons_effect),
                            Effect(12, true, R.string.max_damage_grenade_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.HURRICANE -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(10, true, R.string.speed_effect)
                        )
                        4, 5 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(10, true, R.string.speed_effect),
                            Effect(5, false, R.string.shield_protection_effect),
                            Effect(5, false, R.string.team_health_up_effect),
                        )
                        6 -> listOf(
                            Effect(30, false, R.string.health_after_team_health_effect),
                            Effect(10, true, R.string.speed_effect),
                            Effect(5, false, R.string.shield_protection_effect),
                            Effect(5, false, R.string.team_health_up_effect),
                            Effect(9, true, R.string.health_damage_effect),
                            Effect(1, false, R.string.shield_duration_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.GHOST -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect)
                        )
                        4, 5 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect),
                            Effect(10, true, R.string.speed_after_invisible_effect),
                            Effect(7, false, R.string.stimulator_health_effect),
                        )
                        6 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect),
                            Effect(10, true, R.string.speed_after_invisible_effect),
                            Effect(7, false, R.string.stimulator_health_effect),
                            Effect(9, true, R.string.armor_damage_effect),
                            Effect(5, true, R.string.invisible_damage_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.FREDDIE -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-21, true, R.string.reloading_time_effect)
                        )
                        4, 5 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(30, false, R.string.max_radius_grenade_effect),
                            Effect(7, false, R.string.stimulator_health_effect),
                        )
                        6 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(30, false, R.string.max_radius_grenade_effect),
                            Effect(7, false, R.string.stimulator_health_effect),
                            Effect(9, true, R.string.fire_rate_effect),
                            Effect(20, true, R.string.max_damage_grenade_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.ANGEL -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-21, true, R.string.reloading_time_effect)
                        )
                        4, 5 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(2, false, R.string.shield_protection_effect),
                            Effect(7, false, R.string.stimulator_health_effect),
                        )
                        6 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(2, false, R.string.shield_protection_effect),
                            Effect(7, false, R.string.stimulator_health_effect),
                            Effect(5, false, R.string.add_patrons_effect),
                            Effect(1, false, R.string.shield_duration_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.RAVEN -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect)
                        )
                        4, 5 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect),
                            Effect(2, false, R.string.scan_duration_effect),
                            Effect(7, false, R.string.stimulator_health_effect),
                        )
                        6 -> listOf(
                            Effect(5, true, R.string.stimulator_speed_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect),
                            Effect(2, false, R.string.scan_duration_effect),
                            Effect(7, false, R.string.stimulator_health_effect),
                            Effect(5, false, R.string.add_patrons_effect),
                            Effect(7, true, R.string.scan_damage_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.BLOT -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(9, true, R.string.armor_damage_effect)
                        )
                        4, 5 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(9, true, R.string.armor_damage_effect),
                            Effect(5, false, R.string.wall_protection_effect),
                            Effect(30, false, R.string.band_health_up_effect),
                        )
                        6 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(9, true, R.string.armor_damage_effect),
                            Effect(5, false, R.string.wall_protection_effect),
                            Effect(30, false, R.string.band_health_up_effect),
                            Effect(9, true, R.string.health_damage_effect),
                            Effect(15, false, R.string.fire_range_while_wall_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.FIREFLY -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(10, true, R.string.max_health_effect)
                        )
                        4, 5 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(10, true, R.string.max_health_effect),
                            Effect(50, false, R.string.max_radius_grenade_effect),
                            Effect(30, false, R.string.band_health_up_effect),
                        )
                        6 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(10, true, R.string.max_health_effect),
                            Effect(50, false, R.string.max_radius_grenade_effect),
                            Effect(30, false, R.string.band_health_up_effect),
                            Effect(8, false, R.string.piercing_power_effect),
                            Effect(10, true, R.string.max_damage_grenade_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.SLAYER -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(-21, true, R.string.reloading_time_effect)
                        )
                        4, 5 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(1, false, R.string.vision_duration_effect),
                            Effect(30, false, R.string.band_health_up_effect),
                        )
                        6 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(1, false, R.string.vision_duration_effect),
                            Effect(30, false, R.string.band_health_up_effect),
                            Effect(-18, true, R.string.aiming_speed_effect),
                            Effect(2, false, R.string.piercing_vision_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.MIRAGE -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(-21, true, R.string.reloading_time_effect)
                        )
                        4, 5 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(50, false, R.string.jump_distance_effect),
                            Effect(30, false, R.string.band_health_up_effect),
                        )
                        6 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(50, false, R.string.jump_distance_effect),
                            Effect(30, false, R.string.band_health_up_effect),
                            Effect(10, true, R.string.max_armor_effect),
                            Effect(5, true, R.string.speed_after_jump_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.LYNX -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(10, true, R.string.max_armor_effect)
                        )
                        4, 5 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(10, true, R.string.max_armor_effect),
                            Effect(25, false, 0),
                            Effect(30, false, R.string.band_health_up_effect)
                        )
                        6 -> listOf(
                            Effect(-20, true, R.string.band_running_volume_effect),
                            Effect(10, true, R.string.max_armor_effect),
                            Effect(25, false, 0),
                            Effect(30, false, R.string.band_health_up_effect),
                            Effect(7, false, R.string.piercing_power_effect),
                            Effect(10, true, R.string.piercing_power_effect)
                        )
                        else -> listOf()
                    }
                }
                HeroList.SMOG -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(10, true, R.string.max_armor_effect)
                        )
                        4, 5 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(10, true, R.string.max_armor_effect),
                            Effect(50, false, R.string.max_radius_rocket_effect),
                            Effect(10, true, R.string.tank_heal_on_activate_effect),
                        )
                        6 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(10, true, R.string.max_armor_effect),
                            Effect(50, false, R.string.max_radius_rocket_effect),
                            Effect(10, true, R.string.tank_heal_on_activate_effect),
                            Effect(9, true, R.string.health_damage_effect),
                            Effect(10, true, R.string.max_damage_rocket_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.DRAGOON -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(10, true, R.string.max_health_effect),
                        )
                        4, 5 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(10, true, R.string.max_health_effect),
                            Effect(50, false, R.string.jump_distance_effect),
                            Effect(10, true, R.string.tank_heal_on_activate_effect),
                        )
                        6 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(10, true, R.string.max_health_effect),
                            Effect(50, false, R.string.jump_distance_effect),
                            Effect(10, true, R.string.tank_heal_on_activate_effect),
                            Effect(10, true, R.string.armor_damage_effect),
                            Effect(-75, false, R.string.jump_damage_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.BASTION -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(-29, true, R.string.spread_in_focus_effect)
                        )
                        4, 5 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(-29, true, R.string.spread_in_focus_effect),
                            Effect(3, false, R.string.shield_protection_effect),
                            Effect(10, true, R.string.tank_heal_on_activate_effect),
                        )
                        6 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(-29, true, R.string.spread_in_focus_effect),
                            Effect(3, false, R.string.shield_protection_effect),
                            Effect(10, true, R.string.tank_heal_on_activate_effect),
                            Effect(10, true, R.string.max_health_effect),
                            Effect(1, false, R.string.shield_duration_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.BERTHA -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(10, true, R.string.max_armor_effect),
                        )
                        4, 5 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(10, true, R.string.max_armor_effect),
                            Effect(-10, true, R.string.reloading_after_suppression_effect),
                            Effect(10, true, R.string.tank_heal_on_activate_effect),
                        )
                        6 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(10, true, R.string.max_armor_effect),
                            Effect(-10, true, R.string.reloading_after_suppression_effect),
                            Effect(10, true, R.string.tank_heal_on_activate_effect),
                            Effect(7, false, R.string.piercing_power_effect),
                            Effect(1, false, R.string.piercing_on_suppression_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.LEVIATHAN -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect),
                        )
                        4, 5 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect),
                            Effect(10, true, R.string.turret_damage_effect),
                            Effect(10, true, R.string.tank_heal_on_activate_effect),
                        )
                        6 -> listOf(
                            Effect(10, true, R.string.max_health_after_tank_heal_effect),
                            Effect(-18, true, R.string.spread_in_not_focus_effect),
                            Effect(10, true, R.string.turret_damage_effect),
                            Effect(10, true, R.string.tank_heal_on_activate_effect),
                            Effect(10, true, R.string.speed_effect),
                            Effect(9, true, R.string.turret_health_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.STALKER -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(10, true, R.string.speed_effect),
                        )
                        4, 5 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(10, true, R.string.speed_effect),
                            Effect(1, false, R.string.invisible_duration_effect),
                            Effect(-20, true, R.string.buff_damage_trooper_effect),
                        )
                        6 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(10, true, R.string.speed_effect),
                            Effect(1, false, R.string.invisible_duration_effect),
                            Effect(-20, true, R.string.buff_damage_trooper_effect),
                            Effect(9, true, R.string.armor_damage_effect),
                            Effect(5, true, R.string.invisible_damage_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.DOC -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(9, true, R.string.fire_rate_effect),
                        )
                        4, 5 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(9, true, R.string.fire_rate_effect),
                            Effect(50, false, R.string.max_radius_rocket_effect),
                            Effect(-20, true, R.string.buff_damage_trooper_effect),
                        )
                        6 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(9, true, R.string.fire_rate_effect),
                            Effect(50, false, R.string.max_radius_rocket_effect),
                            Effect(-20, true, R.string.buff_damage_trooper_effect),
                            Effect(9, true, R.string.health_damage_effect),
                            Effect(10, true, R.string.max_damage_rocket_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.LEVI -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                        )
                        4, 5 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(1, false, R.string.vision_duration_effect),
                            Effect(-20, true, R.string.buff_damage_trooper_effect),
                        )
                        6 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(-21, true, R.string.reloading_time_effect),
                            Effect(1, false, R.string.vision_duration_effect),
                            Effect(-20, true, R.string.buff_damage_trooper_effect),
                            Effect(-29, true, R.string.spread_in_focus_effect),
                            Effect(2, false, R.string.piercing_vision_effect),
                        )
                        else -> listOf()
                    }
                }
                HeroList.SATOSHI -> {
                    when (count) {
                        2, 3 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(-18, true, R.string.spread_in_focus_effect)
                        )
                        4, 5 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(-18, true, R.string.spread_in_focus_effect),
                            Effect(1, false, R.string.wall_duration_effect),
                            Effect(-20, true, R.string.buff_damage_trooper_effect),
                        )
                        6 -> listOf(
                            Effect(10, false, R.string.buff_health_trooper_effect),
                            Effect(-18, true, R.string.spread_in_focus_effect),
                            Effect(1, false, R.string.wall_duration_effect),
                            Effect(-20, true, R.string.buff_damage_trooper_effect),
                            Effect(21, false, R.string.fire_range_focus_effect),
                            Effect(5, false, R.string.wall_protection_effect),
                        )
                        else -> listOf()
                    }
                }
                else -> listOf()
            }
        }
    }
}